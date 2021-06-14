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

    <a-row>
      <a-col :span="24" id="main-col">
        <div id="pieChart" style="width:100%;height:400px;"></div>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="24" id="main-col">
        <div id="wordCloud" style="width:100%;height:400px;"></div>
      </a-col>
    </a-row>

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


    const testEcharts = () => {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      const option = {
        title: {
          text: '柱状图'
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
       title: {
         text: '日历图'
       },
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

   const testPieChart = () => {
     const myPieChartChart = echarts.init(document.getElementById('pieChart'));

     const dataAll = [389, 259, 262, 324, 232, 176, 196, 214, 133, 370];
     const yAxisData = [
       '原因1',
       '原因2',
       '原因3',
       '原因4',
       '原因5',
       '原因6',
       '原因7',
       '原因8',
       '原因9',
       '原因10',
     ];

      var option = {
  backgroundColor: '#525288',
  title: [
    {
      text: '各渠道投诉占比',
      left: '2%',
      top: '1%',
      textStyle: {
        color: '#fff',
        fontSize: 14,
      },
    },
    {
      text: '投诉原因TOP10',
      left: '40%',
      top: '1%',
      textStyle: {
        color: '#fff',
        fontSize: 14,
      },
    },
    {
      text: '各级别投诉占比',
      left: '2%',
      top: '50%',
      textStyle: {
        color: '#fff',
        fontSize: 14,
      },
    },
  ],
  grid: [{ left: '50%', top: '7%', width: '45%', height: '90%' }],
  tooltip: {
    formatter: '{b} ({c})',
  },
  xAxis: [
    {
      gridIndex: 0,
      axisTick: { show: false },
      axisLabel: { show: false },
      splitLine: { show: false },
      axisLine: { show: false },
    },
  ],
  yAxis: [
    {
      gridIndex: 0,
      interval: 0,
      data: yAxisData.reverse(),
      axisTick: { show: false },
      axisLabel: { show: true },
      splitLine: { show: false },
      axisLine: { show: true, lineStyle: { color: '#6173a3' } },
    },
  ],
  series: [
    {
      name: '各渠道投诉占比',
      type: 'pie',
      radius: '30%',
      center: ['22%', '25%'],
      data: [
        { value: 335, name: '客服电话' },
        { value: 310, name: '奥迪官网' },
        { value: 234, name: '媒体曝光' },
        { value: 135, name: '质检总局' },
        { value: 105, name: '其他' },
      ],
      labelLine: { show: false },
      label: {
        show: true,
        formatter: '{b} \n ({d}%)',
        color: '#B1B9D3',
      },
    },
    {
      name: '各级别投诉占比',
      type: 'pie',
      radius: '30%',
      center: ['22%', '75%'],
      labelLine: { show: false },
      data: [
        { value: 335, name: 'A级' },
        { value: 310, name: 'B级' },
        { value: 234, name: 'C级' },
        { value: 135, name: 'D级' },
      ],
      label: {
        show: true,
        formatter: '{b} \n ({d}%)',
        color: '#B1B9D3',
      },
    },
    {
      name: '投诉原因TOP10',
      type: 'bar',
      xAxisIndex: 0,
      yAxisIndex: 0,
      barWidth: '45%',
      itemStyle: { color: '#86c9f4' },
      label: { show: true, position: 'right', color: '#9EA7C4' },
      data: dataAll.sort(),
    },
  ],
}


     // 使用刚指定的配置项和数据显示图表。
     myPieChartChart.setOption(option);

   };

    const testWordCloud = () => {
      // 基于准备好的dom，初始化echarts实例
      const myWordCloud = echarts.init(document.getElementById('wordCloud'));

      // 指定图表的配置项和数据
      const option ={
        title: {
          text: '词云',
          x:"center"
        },

        backgroundColor:"#c6fbff",//tooltip: {//pointFormat: "{series.name}: {point.percentage:.1f}%"//},
        series: [
          {
            type:"wordCloud",//用来调整词之间的距离
            gridSize: 10,//用来调整字的大小范围//Text size range which the value in data will be mapped to.//Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 60],//Text rotation range and step in degree. Text will be rotated randomly in range [-90, 90] by rotationStep 45//用来调整词的旋转方向，，[0,0]--表明着没有角度，也就是词为水平方向，须要设置角度参考注释内容//rotationRange: [-45, 0, 45, 90],//rotationRange: [ 0,90],
            rotationRange: [0, 0],//随机生成字体颜色//maskImage: maskImage,
            textStyle: {
              // normal: {
              //   color: function() {return("rgb(" +Math.round(Math.random()* 255) +
              //
              //       "," +Math.round(Math.random()* 255) +
              //
              //       "," +Math.round(Math.random()* 255) +
              //
              //       ")");
              //
              //   }
              // }
            },
            //位置相关设置//Folllowing left/top/width/height/right/bottom are used for positioning the word cloud//Default to be put in the center and has 75% x 80% size.
            left: "center",
            top:"center",
            right:null,
            bottom:null,
            width:"200%",
            height:"200%",//数据
            data: [
              {
                name: "十九大精神",
                value: 15000
              },
              {
                name: "两学一作",
                value: 10081
              },
              {
                name: "中华民族",
                value: 9386
              },
              {
                name: "马克思主义",
                value: 7500
              },
              {
                name: "民族复兴",
                value: 7500
              },
              {
                name: "社会主义制度",
                value: 6500
              },
              {
                name: "国防白皮书",
                value: 6500
              },
              {
                name: "创新",
                value: 6000
              },
              {
                name: "民主革命",
                value: 4500
              },
              {
                name: "文化强国",
                value: 3800
              },
              {
                name: "国家主权",
                value: 3000
              },
              {
                name: "武装颠覆",
                value: 2500
              },
              {
                name: "领土完整",
                value: 2300
              },
              {
                name: "安全",
                value: 2000
              },
              {
                name: "从严治党",
                value: 1900
              },
              {
                name: "现代化经济体系",
                value: 1800
              },
              {
                name: "国防动员",
                value: 1700
              },
              {
                name: "信息化战争",
                value: 1600
              },
              {
                name: "局部战争",
                value: 1500
              },
              {
                name: "教育",
                value: 1200
              },
              {
                name: "职业教育",
                value: 1100
              },
              {
                name: "兵法",
                value: 900
              },
              {
                name: "一国两制",
                value: 800
              },
              {
                name: "特点社会主义思想",
                value: 700
              },
            ]
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myWordCloud.setOption(option);
    };


    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");

      testEcharts();
      testCalendarChart();
      testPieChart();
      testWordCloud();


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