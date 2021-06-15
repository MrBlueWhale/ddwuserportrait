<template>
  <a-layout>

    <h1 class="h1">这是基础标签模块的页面</h1>


    <a-tabs v-model:activeKey="activeKey" >
      <a-tab-pane key="basetag-statistic" >
        <template #tab>
        <span @click="handleClick('welcome')">
         <icon-font class="icons-bar" type="icon-tongji1" style="font-size: 18px"/>
          标签统计
        </span>
        </template>

        <a-row>
          <a-col :span="24" id="main-col">
            <div id="tag-statistic-sun" style="width:50%;height:600px;"></div>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24" id="main-col">
            <div id="tag-statistic" style="width:50%;height:600px;"></div>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24" id="main-col">
            <div id="main2" style="width:100%;height:800px;"></div>
          </a-col>
        </a-row>

      </a-tab-pane>
      <a-tab-pane key="tag-process" >
        <template #tab >
        <span @click="handleClick('tag-process')">
          <icon-font class="icons-bar" type="icon-jindutiao" style="font-size: 18px"/>
          标签进度管理
        </span>
        </template>
        开发中
      </a-tab-pane>
      <a-tab-pane key="tag-audit" >
        <template #tab>
        <span @click="handleClick('tag-audit')">
          <icon-font class="icons-bar" type="icon-shenhe" style="font-size: 20px"/>
          审核状态管理
        </span>
        </template>
        未通过
      </a-tab-pane>
    </a-tabs>








  </a-layout>
</template>


<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

import {createFromIconfontCN} from "@ant-design/icons-vue/es/index";
import store from "@/store";


const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_2612795_r0pnkbi5lnb.js',
});


import { groupItems } from './data/tagdata';




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

    const tagStatistic = () => {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('tag-statistic'));

      // 指定图表的配置项和数据

      const colors = ['#FFAE57', '#FF7853', '#EA5151', '#CC3F57', '#9A2555'];
      const bgColor = '#2E2733';

      const itemStyle:any = {
        star5: {
          color: colors[0]
        },
        star4: {
          color: colors[1]
        },
        star3: {
          color: colors[2]
        },
        star2: {
          color: colors[3]
        }
      };

      const data:any = [
          {
        name: '虚构',
        itemStyle: {
          color: colors[1]
        },
        children: [{
          name: '小说',
          children: [{
            name: '5☆',
            children: [{
              name: '疼'
            }, {
              name: '慈悲'
            }, {
              name: '楼下的房客'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '虚无的十字架'
            }, {
              name: '无声告白'
            }, {
              name: '童年的终结'
            }]
          }, {
            name: '3☆',
            children: [{
              name: '疯癫老人日记'
            }]
          }]
        }, {
          name: '其他',
          children: [{
            name: '5☆',
            children: [{
              name: '纳博科夫短篇小说全集'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '安魂曲'
            }, {
              name: '人生拼图版'
            }]
          }, {
            name: '3☆',
            children: [{
              name: '比起爱你，我更需要你'
            }]
          }]
        }]
      }, {
        name: '非虚构',
        itemStyle: {
          color: colors[2]
        },
        children: [
            {
          name: '设计',
          children: [{
            name: '5☆',
            children: [{
              name: '无界面交互'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '数字绘图的光照与渲染技术'
            }, {
              name: '日本建筑解剖书'
            }]
          }, {
            name: '3☆',
            children: [{
              name: '奇幻世界艺术\n&RPG地图绘制讲座'
            }]
          }]
        }, {
          name: '社科',
          children: [{
            name: '5☆',
            children: [{
              name: '痛点'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '卓有成效的管理者'
            }, {
              name: '进化'
            }, {
              name: '后物欲时代的来临'
            }]
          }, {
            name: '3☆',
            children: [{
              name: '疯癫与文明'
            }]
          }]
        }, {
          name: '心理',
          children: [{
            name: '5☆',
            children: [{
              name: '我们时代的神经症人格'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '皮格马利翁效应'
            }, {
              name: '受伤的人'
            }]
          }, {
            name: '3☆'
          }, {
            name: '2☆',
            children: [{
              name: '迷恋'
            }]
          }]
        }, {
          name: '居家',
          children: [{
            name: '4☆',
            children: [{
              name: '把房子住成家'
            }, {
              name: '只过必要生活'
            }, {
              name: '北欧简约风格'
            }]
          }]
        }, {
          name: '绘本',
          children: [{
            name: '5☆',
            children: [{
              name: '设计诗'
            }]
          }, {
            name: '4☆',
            children: [{
              name: '假如生活糊弄了你'
            }, {
              name: '博物学家的神秘动物图鉴'
            }]
          }, {
            name: '3☆',
            children: [{
              name: '方向'
            }]
          }]
        }, {
          name: '哲学',
          children: [{
            name: '4☆',
            children: [{
              name: '人生的智慧'
            }]
          }]
        }, {
          name: '技术',
          children: [{
            name: '5☆',
            children: [{
              name: '代码整洁之道'
            }]
          }, {
            name: '4☆',
            children: [{
              name: 'Three.js 开发指南'
            }]
          }]
        }]
      }];

      for (var j = 0; j < data.length; ++j) {
        let level1 = data[j].children;
        for (var i = 0; i < level1.length; ++i) {
          let block:any = level1[i].children;
          let bookScore:any = [];
          let bookScoreId = 0;
          for (var star = 0; star < block.length; ++star) {
            var style = (function (name:string) {
              switch (name) {
                case '5☆':
                  bookScoreId = 0;
                  return itemStyle.star5;
                case '4☆':
                  bookScoreId = 1;
                  return itemStyle.star4;
                case '3☆':
                  bookScoreId = 2;
                  return itemStyle.star3;
                case '2☆':
                  bookScoreId = 3;
                  return itemStyle.star2;
              }
            })(block[star].name);

            block[star].label = {
              color: style.color,
              downplay: {
                opacity: 0.5
              }
            };

            if (block[star].children) {
              style = {
                opacity: 1,
                color: style.color
              };

              block[star].children.forEach(function (book:any) {
                book.value = 1;
                book.itemStyle = style;

                book.label = {
                  color: style.color
                };

                var value = 1;
                if (bookScoreId === 0 || bookScoreId === 3) {
                  value = 5;
                }

                if (bookScore[bookScoreId]) {
                  bookScore[bookScoreId].value += value;
                }
                else {
                  bookScore[bookScoreId] = {
                    color: colors[bookScoreId],
                    value: value
                  };
                }
              });
            }
          }

          level1[i].itemStyle = {
            color: data[j].itemStyle.color
          };
        }
      }

       let option = {
        backgroundColor: bgColor,
        color: colors,
        series: [{
          type: 'sunburst',
          center: ['50%', '48%'],
          data: data,
          sort: function (a:any, b:any) {
            if (a.depth === 1) {
              return b.getValue() - a.getValue();
            }
            else {
              return a.dataIndex - b.dataIndex;
            }
          },
          label: {
            rotate: 'radial',
            color: bgColor
          },
          itemStyle: {
            borderColor: bgColor,
            borderWidth: 2
          },
          levels: [{}, {
            r0: 0,
            r: 40,
            label: {
              rotate: 0
            }
          }, {
            r0: 40,
            r: 105
          }, {
            r0: 115,
            r: 140,
            itemStyle: {
              shadowBlur: 2,
              shadowColor: colors[2],
              color: 'transparent'
            },
            label: {
              rotate: 'tangential',
              fontSize: 10,
              color: colors[0]
            }
          }, {
            r0: 140,
            r: 145,
            itemStyle: {
              shadowBlur: 80,
              shadowColor: colors[0]
            },
            label: {
              position: 'outside',
              textShadowBlur: 5,
              textShadowColor: '#333'
            },
            downplay: {
              label: {
                opacity: 0.5
              }
            }
          }]
        }]
      };


      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    };

    const tagTreeStatistic = () => {

      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('main2'));

      // for(let i=0; i< groupItems.length; i++){
      //   // if(groupItems[i].children !== undefined){
      //   // if('children' in groupItems[i]){
      //   // if(groupItems[i].hasOwnProperty('children')){
      //   if(groupItems[i].children){
      //
      //     let groupChildren = groupItems[i].children;
      //
      //     groupChildren.forEach(function (datum:any, index:any) {
      //       index % 2 === 0 && (datum.collapsed = true);
      //     });
      //
      //   }
      //
      // }

      let option = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',

            data: groupItems,

            top: '1%',
            left: '7%',
            bottom: '1%',
            right: '20%',

            symbolSize: 7,

            label: {
              position: 'left',
              verticalAlign: 'middle',
              align: 'right',
              fontSize: 9
            },

            leaves: {
              label: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left'
              }
            },

            emphasis: {
              focus: 'descendant'
            },

            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }


      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);

    };

    const tagStatisticSun = () => {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('tag-statistic-sun'));

      // 指定图表的配置项和数据

      const colors = ['#FFAE57', '#FF7853', '#EA5151', '#CC3F57', '#9A2555'];
      const bgColor = '#2E2733';

      const itemStyle:any = {
        star5: {
          color: colors[0]
        },
        star4: {
          color: colors[1]
        },
        star3: {
          color: colors[2]
        },
        star2: {
          color: colors[3]
        }
      };

      const data:any = [
        {
          name: '人口属性',
          itemStyle: {
            color: colors[1]
          },
          children: [{
            name: '性别',
            children: [{
              name: '已通过',
              children: [{
                name: '男'
              }]
            }, {
              name: '未通过',
              children: [{
                name: '女'
              }]
            }]
          }]
        },

        {
          name: '商业属性',
          itemStyle: {
            color: colors[2]
          },
          children: [
            {
              name: '心理',
              children: [{
                name: '5☆',
                children: [{
                  name: '我们时代的神经症人格'
                }]
              }, {
                name: '已通过',
                children: [{
                  name: '皮格马利翁效应'
                }, {
                  name: '受伤的人'
                }]
              }, {
                name: '已通过'
              }, {
                name: '未通过',
                children: [{
                  name: '迷恋'
                }]
              }]
            },
            {
              name: '哲学',
              children: [{
                name: '已通过',
                children: [{
                  name: '人生的智慧'
                }]
              }]
            }
            ]
        },

        {
          name: '行为属性',
          itemStyle: {
            color: colors[3]
          },
          children: [
            {
              name: '心理',
              children: [{
                name: '5☆',
                children: [{
                  name: '我们时代的神经症人格'
                }]
              }, {
                name: '已通过',
                children: [{
                  name: '皮格马利翁效应'
                }, {
                  name: '受伤的人'
                }]
              }, {
                name: '已通过'
              }, {
                name: '未通过',
                children: [{
                  name: '迷恋'
                }]
              }]
            },
            {
              name: '哲学',
              children: [{
                name: '已通过',
                children: [{
                  name: '人生的智慧'
                }]
              }]
            }
          ]
        },

        {
          name: '用户价值',
          itemStyle: {
            color: colors[4]
          },
          children: [
            {
              name: '心理',
              children: [{
                name: '5☆',
                children: [{
                  name: '我们时代的神经症人格'
                }]
              }, {
                name: '已通过',
                children: [{
                  name: '皮格马利翁效应'
                }, {
                  name: '受伤的人'
                }]
              }, {
                name: '已通过'
              }, {
                name: '未通过',
                children: [{
                  name: '迷恋'
                }]
              }]
            },
            {
              name: '哲学',
              children: [{
                name: '已通过',
                children: [{
                  name: '人生的智慧'
                }]
              }]
            }
          ]
        }

        ];

      for (var j = 0; j < data.length; ++j) {
        let level1 = data[j].children;
        for (var i = 0; i < level1.length; ++i) {
          let block:any = level1[i].children;
          let bookScore:any = [];
          let bookScoreId = 0;
          for (var star = 0; star < block.length; ++star) {
            var style = (function (name:string) {
              switch (name) {
                case '5☆':
                  bookScoreId = 0;
                  return itemStyle.star5;
                case '未处理':
                  bookScoreId = 1;
                  return itemStyle.star4;
                case '已通过':
                  bookScoreId = 2;
                  return itemStyle.star3;
                case '未通过':
                  bookScoreId = 3;
                  return itemStyle.star2;
              }
            })(block[star].name);

            block[star].label = {
              color: style.color,
              downplay: {
                opacity: 0.5
              }
            };

            if (block[star].children) {
              style = {
                opacity: 1,
                color: style.color
              };

              block[star].children.forEach(function (book:any) {
                book.value = 1;
                book.itemStyle = style;

                book.label = {
                  color: style.color
                };

                var value = 1;
                if (bookScoreId === 0 || bookScoreId === 3) {
                  value = 5;
                }

                if (bookScore[bookScoreId]) {
                  bookScore[bookScoreId].value += value;
                }
                else {
                  bookScore[bookScoreId] = {
                    color: colors[bookScoreId],
                    value: value
                  };
                }
              });
            }
          }

          level1[i].itemStyle = {
            color: data[j].itemStyle.color
          };
        }
      }

      let option = {
        backgroundColor: bgColor,
        color: colors,
        series: [{
          type: 'sunburst',
          center: ['50%', '48%'],
          data: data,
          sort: function (a:any, b:any) {
            if (a.depth === 1) {
              return b.getValue() - a.getValue();
            }
            else {
              return a.dataIndex - b.dataIndex;
            }
          },
          label: {
            rotate: 'radial',
            color: bgColor
          },
          itemStyle: {
            borderColor: bgColor,
            borderWidth: 2
          },
          levels: [{}, {
            r0: 0,
            r: 40,
            label: {
              rotate: 0
            }
          }, {
            r0: 40,
            r: 105
          }, {
            r0: 115,
            r: 140,
            itemStyle: {
              shadowBlur: 2,
              shadowColor: colors[2],
              color: 'transparent'
            },
            label: {
              rotate: 'tangential',
              fontSize: 10,
              color: colors[0]
            }
          }, {
            r0: 140,
            r: 145,
            itemStyle: {
              shadowBlur: 80,
              shadowColor: colors[0]
            },
            label: {
              position: 'outside',
              textShadowBlur: 5,
              textShadowColor: '#333'
            },
            downplay: {
              label: {
                opacity: 0.5
              }
            }
          }]
        }]
      };


      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    };


    const activeKey = ref('basetag-statistic')


    //初始化逻辑都写到onMounted()里
    onMounted(() => {
      console.log("onMounted");

      tagStatistic();

      tagTreeStatistic();

      tagStatisticSun();



    });

    //html代码要拿到响应式变量 需要在setup的最后return
    return {

      statistic,

      activeKey,

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

    IconFont,

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