<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <a-layout>

    <h1 class="h1">这是组合标签模块的页面</h1>

    <p>
      <a-form layout="inline" :model="param">
        <a-form-item>
          <a-input v-model:value="param.desc" placeholder="标签描述">
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="handleQuery()">
            查询
          </a-button>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="createGroupTag()">
            创建标签
          </a-button>
        </a-form-item>
      </a-form>
    </p>
    <a-table
            :columns="columns"
            :row-key="record => record.gtId"
            :data-source="groupTags"
            :loading="loading"
            @change="handleTableChange"
    >

      <template #baseTagNames="{ text: baseTagNames }">
      <span>
        <a-tag
                v-for="tag in baseTagNames"
                :key="tag"
                :color="tag.length < 5  ? 'volcano' : tag.length > 6 ? 'geekblue' : 'green'"
        >
          {{ tag.toUpperCase() }}
        </a-tag>
      </span>
      </template>


      <template #tagStatus="{ text: tagStatus, record }">

        <a-tag :color="statusColorMap.get(tagStatus)" :tagStatus="tagStatus" >
          <template #icon>
            <exclamation-circle-outlined/>

          </template>
          {{ statusMap.get(tagStatus) }}
        </a-tag>
      </template>

      <!--      v-if="role.active == 0"-->

      <template v-slot:action="{ text, record }">
        <a-space size="small">

          <a-button type="primary" @click="edit(record)">
            编辑
          </a-button>

          <a-popconfirm
                  title="删除后不可恢复，确认删除?"
                  ok-text="是"
                  cancel-text="否"
                  @confirm="handleDelete(record.gtId)"
          >
            <a-button type="danger">
              删除
            </a-button>
          </a-popconfirm>
        </a-space>

      </template>
    </a-table>

  </a-layout>
</template>


<script lang="ts">
  import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

  import axios from 'axios';
  import { message } from 'ant-design-vue';
// import HelloWorld from "@/components/HelloWorld.vue";

// const listData: Record<string, string>[] = [];





export default defineComponent({
  name: 'PortraitGroupTag',

  //放一些参数定义，方法定义
  setup() {
    console.log("setup");
    // 使用ref()定义响应式数据
    const demos = ref();
    //reactive中放入对象 并自定义属性
    const demos2 = reactive({demos: []});

    let statusMap = new Map();
    statusMap.set(0, '不可用');
    statusMap.set(1, '可用');

    let statusColorMap = new Map();
    statusColorMap.set(0, 'error');
    statusColorMap.set(1, 'success');


    const columns = [
      {
        title: '组合标签ID',
        dataIndex: 'gtId'
      },
      {
        title: '标签描述',
        dataIndex: 'desc'
      },
      // {
      //   title: '密码',
      //   dataIndex: 'password'
      // },
      {
        title: '基础标签',
        key: 'baseTagNames',
        dataIndex: 'baseTagNames',
        slots: { customRender: 'baseTagNames' }

      },

      {
        title: '标签状态',
        dataIndex: 'tagStatus',
        slots: { customRender: 'tagStatus' }
      },

      // {
      //   title: '电话',
      //   dataIndex: 'telNum'
      // },
      {
        title: '操作',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];


    const params = ref({});
    const groupTags = ref();


    const param = ref();
    param.value = {};
    /**
     * 数据查询
     **/
    // const handleQuery = (params: any) => {
    const handleQuery = () => {


      groupTags.value = [];
      // axios.get("/groupTag/listGroupTags", {params: {}})
      axios.get("/groupTag/listGroupTags")
              .then((response) => {

        const data = response.data;
        if (data.success) {
          groupTags.value = data.content;


        } else {
          message.error(data.message);
        }
      });
    };

    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");
      axios.get("/demo/list").then((response) => {
        const data = response.data;
        //ref数据的赋值
        demos.value = data.content;
        demos2.demos = data.content;

        // console.log(response)
      });

      handleQuery();

    });

    //html代码要拿到响应式变量 需要在setup的最后return
    return {
      demos,
      demos_reactive: toRef(demos2, "demos"),

      groupTags,
      param,

      statusMap,
      statusColorMap,
      columns,

      // pagination : {
      //   onChange: (page: any) => {
      //     console.log(page);
      //   },
      //   pageSize: 3,
      // },
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