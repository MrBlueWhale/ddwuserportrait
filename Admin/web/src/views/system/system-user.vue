<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <a-layout>

    <h1 class="h1">这是用户管理模块的页面</h1>

    <p>
      <a-form layout="inline" :model="param">
        <a-form-item>
          <a-input v-model:value="param.userName" placeholder="登陆名">
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
            查询
          </a-button>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="add()">
            新增
          </a-button>
        </a-form-item>
      </a-form>
    </p>
    <a-table
        :columns="columns"
        :row-key="record => record.uid"
        :data-source="users"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
    >

            <template #roleName="{ text: roleName, record }">

              <a-tag :color="roleColorMap.get(roleName)" :roleName="roleName" >
                <template #icon>
                  <exclamation-circle-outlined/>
                </template>
                {{ roleName }}
              </a-tag>
            </template>

<!--      v-if="role.active == 0"-->

      <template v-slot:action="{ text, record }">
        <a-space size="small">
          <a-button type="primary" @click="resetPassword(record)">
            重置密码
          </a-button>
          <a-button type="primary" @click="edit(record)">
            编辑
          </a-button>
          <a-button type="primary" @click="distributeRole(record)">
            角色分配
          </a-button>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.uid)"
          >
            <a-button type="danger">
              删除
            </a-button>
          </a-popconfirm>
        </a-space>
      </template>
    </a-table>


  </a-layout>

  <a-modal
      title="用户表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="登陆名">
        <a-input v-model:value="user.userName" :disabled="!!user.uid"/>
      </a-form-item>
      <a-form-item label="真实姓名">
        <a-input v-model:value="user.name" />
      </a-form-item>
      <a-form-item label="密码" v-show="!user.uid">
        <a-input v-model:value="user.password" type="password"/>
      </a-form-item>
      <a-form-item label="电话号码" >
        <a-input v-model:value="user.telNum" />
      </a-form-item>
      <a-form-item label="邮箱" >
        <a-input v-model:value="user.email" />
      </a-form-item>
    </a-form>
  </a-modal>

  <a-modal
        title="重置密码"
        v-model:visible="resetModalVisible"
        :confirm-loading="resetModalLoading"
        @ok="handleResetModalOk"
>
  <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
    <a-form-item label="新密码">
      <a-input v-model:value="user.password" type="password"/>
    </a-form-item>
  </a-form>
</a-modal>

  <a-modal
          title="角色分配"
          v-model:visible="roleModalVisible"
          :confirm-loading="roleModalLoading"
          @ok="roleModalOk"
  >
    <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-radio-group v-model:value="selectedRole">
        <a-radio :style="distributeRoleRadioStyle" v-for="item in rolelist" :value="item.roleName" :key="item">{{ item.roleName}} -- {{ item.desc}}</a-radio>
<!--        <a-radio :style="radioStyle" :value="2">Option B</a-radio>-->

      </a-radio-group>
    </a-form>
  </a-modal>


</template>


<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";

declare let hexMd5: any;
declare let KEY: any;

// import HelloWorld from "@/components/HelloWorld.vue";


export default defineComponent({
  name: 'SystemUser',

  //放一些参数定义，方法定义
  setup() {
    console.log("setup");
    // 使用ref()定义响应式数据
    const demos = ref();
    //reactive中放入对象 并自定义属性
    const demos2 = reactive({demos: []});


    const param = ref();
    param.value = {};
    const users = ref();
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0
    });
    const loading = ref(false);


    let roleColorMap = new Map()
    roleColorMap.set('超级管理员', 'red')
    roleColorMap.set('角色管理员', 'green')
    roleColorMap.set('用户管理员', 'cyan')
    roleColorMap.set('标签开发员', 'blue')
    roleColorMap.set('标签维护员', 'purple')
    roleColorMap.set('普通用户', 'orange')
    roleColorMap.set('暂未分配角色', 'gray')


    const columns = [
      {
        title: '登陆名',
        dataIndex: 'userName'
      },
      {
        title: '真实姓名',
        dataIndex: 'name'
      },
      // {
      //   title: '密码',
      //   dataIndex: 'password'
      // },
      {
        title: '角色',
        key: 'roleName',
        dataIndex: 'roleName',
        slots: { customRender: 'roleName' }

      },

      {
        title: '邮箱',
        dataIndex: 'email'
      },{
        title: '电话',
        dataIndex: 'telNum'
      },
      {
        title: '操作',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      users.value = [];
      axios.get("/user/list", {
        params: {
          page: params.page,
          size: params.size,
          userName: param.value.userName
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          users.value = data.content.list;

          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    // -------- 表单 ---------
    const user = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;

      // user.value.password = hexMd5(user.value.password + KEY);
      user.value.password = hexMd5(user.value.password);

      axios.post("/user/save", user.value).then((response) => {
        modalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {
          modalVisible.value = false;

          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      user.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      user.value = {};
    };

/////////////////  分配角色  /////////////////
    const roleRecord = ref({
      uid: 0,
      roleName: ''
    });
    const roleModalVisible = ref(false);
    const roleModalLoading = ref(false);
    const distributeRoleRadioStyle = reactive({
      display: 'block',
      height: '30px',
      lineHeight: '30px',
    });
    //分配角色点击事件
    const rolelist = ref({});
    const distributeRole = (record: any) => {
      roleModalVisible.value = true;
      roleRecord.value = record;
      axios.get("/role/listRole" ).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          rolelist.value = data.content;
        } else {
          message.error("角色列表加载失败，请重试...");
        }
      });

    }


    //分配角色
    const selectedRole = ref('')
    const roleModalOk = () => {
      roleModalLoading.value = true;
      console.log("开始分配角色");
      console.log("uid:", roleRecord.value.uid);
      console.log("分配角色", selectedRole.value);

      axios.post("/role/distribute", {
        uid: roleRecord.value.uid,
        roleName: selectedRole.value,
      }).then((response) => {
        roleModalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {
          roleModalVisible.value = false;

          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }


      });

    };







    //删除
    const handleDelete = (uid: number) => {
      axios.delete("/user/delete/" + uid).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    // -------- 重置密码 ---------
    const resetModalVisible = ref(false);
    const resetModalLoading = ref(false);
    const handleResetModalOk = () => {
      resetModalLoading.value = true;

      // user.value.password = hexMd5(user.value.password + KEY);
      user.value.password = hexMd5(user.value.password);

      axios.post("/user/reset-password", user.value).then((response) => {
        resetModalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {
          resetModalVisible.value = false;

          // 重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 重置密码
     */
    const resetPassword = (record: any) => {
      resetModalVisible.value = true;
      user.value = Tool.copy(record);
      user.value.password = null;
    };



    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");

      handleQuery({
        page: 1,
        size: pagination.value.pageSize,
      });



    });

    //html代码要拿到响应式变量 需要在setup的最后return
    return {

      param,
      users,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleQuery,

      edit,
      add,

      user,
      modalVisible,
      modalLoading,
      handleModalOk,

      handleDelete,

      resetModalVisible,
      resetModalLoading,
      handleResetModalOk,
      resetPassword,

      distributeRole,
      roleModalVisible,
      roleModalLoading,
      roleModalOk,
      rolelist,
      distributeRoleRadioStyle,
      selectedRole,

      roleColorMap,


      actions: [
        { type: 'StarOutlined', text: '156' },
        { type: 'LikeOutlined', text: '156' },
        { type: 'MessageOutlined', text: '2' },
      ],
    }
  },


  components: {

  },

});
</script>




<style scoped>

.h1{
  font-size: 25px;
}

/*img {*/
/*  width: 50px;*/
/*  height: 50px;*/
/*}*/
</style>







<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>