<template>
  <a-layout>

    <!--    <div class="BlankBox" style="marginLeft: 25%">-->

    <!--    </div>-->

    <a-layout-content
            :style="{ background: '#fff', padding: '24px', minHeight: '600px',  }"
    >
      <div style="background: #ececec; padding: 30px">
        <a-card title="个人资料" :bordered="false" style="width: 1000px; marginLeft: 15%;" >
          <a-descriptions title="User Info">
            <a-descriptions-item label="用户名">我是菜鸡</a-descriptions-item>
            <a-descriptions-item label="联系电话">1810000000</a-descriptions-item>
            <br>
            <br>
            <br>
            <br>
            <a-descriptions-item label="角色">超级管理员</a-descriptions-item>
            <a-descriptions-item label="权限">增删改查标签</a-descriptions-item>
          </a-descriptions>
        </a-card>
      </div>

    </a-layout-content>

    <!--    <div class="BlankBox" style="marginLeft: 25%">-->

    <!--    </div>-->


  </a-layout>
</template>

<script lang="ts">
  import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

  import axios from 'axios';
// import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
// import AdminHeader from '@/components/admin-header.vue'; // @ is an alias to /src
// import AdminFooter from '@/components/admin-footer.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Profile',

  //放一些参数定义，方法定义
  setup() {

    const text = "请前往七舍5单元502C找本项目负责人兰鑫！！！联系电话：19981481120";
    const activeKey = ref([]);
    //html代码要拿到响应式变量 需要在setup的最后return
    return {
      activeKey,
      text,

    }
  },

  components: {
    // HelloWorld,
    // AdminHeader,
    // AdminFooter,
  },
});
</script>
