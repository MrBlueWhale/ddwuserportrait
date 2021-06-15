<template>
  <a-layout>

    <h1 class="h1">这是基础标签模块的页面</h1>

  </a-layout>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

// import resizefrom "./mixins/resize";

// import"echarts-wordcloud/dist/echarts-wordcloud";
//
// import"echarts-wordcloud/dist/echarts-wordcloud.min";

import axios from 'axios';

declare let echarts: any;

// import HelloWorld from "@/components/HelloWorld.vue";



export default defineComponent({
  name: 'PortraitBaseTag',

  //放一些参数定义，方法定义
  setup() {
    console.log("setup");

    const statistic = ref();
    statistic.value = {};

    const getStatistic = () => {
      // axios.get('/ebook-snapshot/get-statistic').then((response) => {
      //   const data = response.data;
      //   if (data.success) {
      //     const statisticResp = data.content;
      //     statistic.value.viewCount = statisticResp[1].viewCount;
      //     statistic.value.voteCount = statisticResp[1].voteCount;
      //     statistic.value.todayViewCount = statisticResp[1].viewIncrease;
      //     statistic.value.todayVoteCount = statisticResp[1].voteIncrease;
      //

      // });
    };



    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");



    });

    //html代码要拿到响应式变量 需要在setup的最后return
    return {

      statistic,

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