<template>
  <a-layout>

    <h1 class="h1">这是基础标签模块的页面</h1>

    <a-row>
      <a-col :span="24" id="main-col">
        <div id="main" style="width: 100%;height:300px;"></div>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="24" id="main-col">
        <div id="calendar" style="width:100%;height:400px;"></div>
      </a-col>
    </a-row>

  </a-layout>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

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


    const testEcharts = () => {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      const option = {
        title: {
          text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
          data:['销量']
        },
        xAxis: {
          data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    };

   const testCalendarChart = () => {
     const myCalendarChart = echarts.init(document.getElementById('calendar'));

     // 模拟数据
     function getVirtulData(year: any) {
       year = year || '2021';
       var date = +echarts.number.parseDate(year + '-01-01');
       var end = +echarts.number.parseDate(year + '-12-31');
       var dayTime = 3600 * 24 * 1000;
       var data = [];
       for (var time = date; time <= end; time += dayTime) {
         data.push([
           echarts.format.formatTime('yyyy-MM-dd', time),
           Math.floor(Math.random() * 10000)
         ]);
       }
       return data;
     }
     var option = {
       visualMap: {
         show: false,
         min: 0,
         max: 10000
       },
       calendar: {
         range: '2021'
       },
       series: {
         type: 'heatmap',
         coordinateSystem: 'calendar',
         data: getVirtulData(2021)
       }
     };

     // 使用刚指定的配置项和数据显示图表。
     myCalendarChart.setOption(option);

   };


    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");

      testEcharts();
      testCalendarChart();


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