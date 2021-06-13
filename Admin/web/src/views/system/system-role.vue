<template>
  <a-layout>

    <h1 class="h1">这是角色管理模块的页面</h1>

    <p>
      <a-form layout="inline" :model="param">
        <a-form-item>
          <a-input v-model:value="param.roleName" placeholder="角色名">
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
        :data-source="roles"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
    >
      <template v-slot:action="{ text, record }">
        <a-space size="small">

          <a-button type="primary" @click="edit(record)">
            编辑
          </a-button>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="是"
              cancel-text="否"
              @confirm="handleDelete(record.rid)"
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
      title="角色表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="role" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="角色名">
        <a-input v-model:value="role.roleName" :disabled="!!role.rid"/>
      </a-form-item>
      <a-form-item label="角色描述">
        <a-input v-model:value="role.desc" />
      </a-form-item>
<!--      <a-form-item label="密码" v-show="!role.rid">-->
<!--        <a-input v-model:value="role.password" type="password"/>-->
<!--      </a-form-item>-->

    </a-form>
  </a-modal>

</template>


<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";



export default defineComponent({
  name: 'SystemRole',

  //放一些参数定义，方法定义
  setup() {
    console.log("setup");
    // 使用ref()定义响应式数据
    const demos = ref();
    //reactive中放入对象 并自定义属性
    const demos2 = reactive({demos: []});


    const param = ref();
    param.value = {};
    const roles = ref();
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '角色名',
        dataIndex: 'roleName'
      },
      {
        title: '描述',
        dataIndex: 'desc'
      },
      // {
      //   title: '密码',
      //   dataIndex: 'password'
      // },
      {
        title: 'Action',
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
      roles.value = [];
      axios.get("/role/list", {
        params: {
          page: params.page,
          size: params.size,
          roleName: param.value.roleName
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          roles.value = data.content.list;

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
    const role = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;

      // role.value.password = hexMd5(role.value.password + KEY);

      axios.post("/role/save", role.value).then((response) => {
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
      role.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      role.value = {};
    };

    const handleDelete = (rid: number) => {
      axios.delete("/role/delete/" + rid).then((response) => {
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

      axios.post("/role/reset-password", role.value).then((response) => {
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
      roles,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleQuery,

      edit,
      add,

      role,
      modalVisible,
      modalLoading,
      handleModalOk,

      handleDelete,

      resetModalVisible,
      resetModalLoading,
      handleResetModalOk,


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