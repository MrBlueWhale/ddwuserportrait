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

<!--        <a-row>-->
<!--          <a-col :span="24" id="main-col">-->
<!--            <div id="tag-statistic-sun" style="width:50%;height:600px;"></div>-->
<!--          </a-col>-->
<!--        </a-row>-->

        <a-row>
          <a-col :span="24" id="main-col">
            <div id="tag-statistic" style="width:65%;height:1000px;margin-left: 20%;"></div>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24" id="main-col">
            <div id="main2" style="width:100%;height:800px;"></div>
          </a-col>
        </a-row>

      </a-tab-pane>


      <a-tab-pane key="tag-process">
        <template #tab>
        <span @click="handleClick('tag-process')">
          <icon-font class="icons-bar" type="icon-jindutiao" style="font-size: 18px"/>
          标签进度管理
        </span>
        </template>

        <p>
          <a-form layout="inline" :model="param">
            <a-form-item>
              <a-input v-model:value="param.tagName" placeholder="标签名">
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
                查询
              </a-button>
            </a-form-item>
            <a-form-item>
              <a-button type="primary" @click="add()">
                标签申请
              </a-button>
            </a-form-item>
          </a-form>
        </p>

        <a-table :row-key="record => record.btId" :columns="columns" :data-source="baseTags"
                 :row-selection="rowSelection">

          <template #processStatus="{ text: processStatus, record }">

            <a-tag :color="processStatusColorMap.get(processStatus)" :processStatus="processStatus">
              <template #icon>
                <exclamation-circle-outlined/>
              </template>
              {{ processStatusMap.get(processStatus) }}
            </a-tag>
          </template>

          <template v-slot:action="{ text, record }">
            <a-space size="small">
              <a-button type="primary" @click="edit(record.btId)">
                编辑
              </a-button>
              <a-popconfirm
                      title="禁用后标签无法使用，确认禁用?"
                      ok-text="是"
                      cancel-text="否"
                      @confirm="handleban(record)"
              >
                <a-button type="danger" :disabled="record.processStatus===6" v-show="record.btId>199">
                  禁用
                </a-button>
              </a-popconfirm>
              <a-button type="primary" @click="online(record)" :disabled="record.processStatus===4"
                        v-show="record.btId>199">
                上线
              </a-button>
              <a-popconfirm
                      title="下线后标签不可见，确认下线?"
                      ok-text="是"
                      cancel-text="否"
                      @confirm="offline(record.btId)"
              >
                <a-button type="danger" :disabled="record.processStatus===5" v-show="record.btId>199">
                  下线
                </a-button>
              </a-popconfirm>
            </a-space>
          </template>


        </a-table>


      </a-tab-pane>


      <a-tab-pane key="tag-audit">
        <template #tab>
        <span @click="handleClick('tag-audit')">
          <icon-font class="icons-bar" type="icon-shenhe" style="font-size: 20px"/>
          审核状态管理
        </span>
        </template>
        <a-table :row-key="record => record.btId" :columns="columnsAudit" :data-source="baseTagsAudit"
                 :row-selection="rowSelectionAudit">

          <template #auditStatus="{ text: auditStatus, record }">

            <a-tag :color="auditStatusColorMap.get(auditStatus)" :auditStatus="auditStatus">
              <template #icon>
                <exclamation-circle-outlined/>
              </template>
              {{ auditStatusMap.get(auditStatus) }}
            </a-tag>
          </template>

          <template v-slot:action="{ text, record }">
            <a-space size="small">
              <a-popconfirm
                      title="通过后标签可进行开发，确认通过?"
                      ok-text="是"
                      cancel-text="否"
                      @confirm="pass(record)"
              >
                <a-button type="primary" >
                  通过
                </a-button>
              </a-popconfirm>

              <a-popconfirm
                      title="驳回后标签需要修改，确认驳回?"
                      ok-text="是"
                      cancel-text="否"
                      @confirm="handelReject(record)"
              >
                <a-button type="danger" :disabled="record.auditStatus===2" v-show="record.btId>199">
                  驳回
                </a-button>
              </a-popconfirm>

            </a-space>
          </template>


        </a-table>

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
  import {Tool} from '@/util/tool';
  import {message} from 'ant-design-vue';

  // import resizefrom "./mixins/resize";

  // import"echarts-wordcloud/dist/echarts-wordcloud";
  //
  // import"echarts-wordcloud/dist/echarts-wordcloud.min";

  import axios from 'axios';

  declare let echarts: any;

  // import HelloWorld from "@/components/HelloWorld.vue";

  interface TagItem {
    btId: number;
    btName: string;
    parentId: number;
    processStatus: number;
    auditStatus: number;
    desc: string;
    children?: TagItem[]
  }

  const columns = [
    {
      title: '标签名',
      dataIndex: 'btName',
      key: 'btName',
    },
    {
      title: '进度状态',
      dataIndex: 'processStatus',
      key: 'processStatus',
      slots: {customRender: 'processStatus'}
      // width: '12%',
    },
    {
      title: '标签描述',
      dataIndex: 'desc',
      width: '30%',
      key: 'desc',
    },
    {
      title: '操作',
      key: 'action',
      slots: {customRender: 'action'}
    }
  ];

  const rowSelection = {
    onChange: (selectedRowKeys: (string | number)[], selectedRows: TagItem[]) => {
      console.log("onChange");
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
    onSelect: (record: TagItem, selected: boolean, selectedRows: TagItem[]) => {
      console.log("onSelect");
      console.log(record, selected, selectedRows);
    },
    onSelectAll: (selected: boolean, selectedRows: TagItem[], changeRows: TagItem[]) => {
      console.log("onSelectAll");
      console.log(selected, selectedRows, changeRows);
    },
  };


  const columnsAudit = [
    {
      title: '标签名',
      dataIndex: 'btName',
      key: 'btName',
    },
    {
      title: '审核状态',
      dataIndex: 'auditStatus',
      key: 'auditStatus',
      slots: {customRender: 'auditStatus'}
      // width: '12%',
    },
    {
      title: '标签描述',
      dataIndex: 'desc',
      width: '30%',
      key: 'desc',
    },
    {
      title: '操作',
      key: 'action',
      slots: {customRender: 'action'}
    }
  ];

  const rowSelectionAudit = {
    onChange: (selectedRowKeys: (string | number)[], selectedRows: TagItem[]) => {
      console.log("onChange");
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
    onSelect: (record: TagItem, selected: boolean, selectedRows: TagItem[]) => {
      console.log("onSelect");
      console.log(record, selected, selectedRows);
    },
    onSelectAll: (selected: boolean, selectedRows: TagItem[], changeRows: TagItem[]) => {
      console.log("onSelectAll");
      console.log(selected, selectedRows, changeRows);
    },
  };






  export default defineComponent({
    name: 'PortraitBaseTag',

    //放一些参数定义，方法定义
    setup() {
      console.log("setup");

      let processStatusMap = new Map;

      processStatusMap.set(1, "申请中");
      processStatusMap.set(2, "开发中");
      processStatusMap.set(3, "开发完成");
      processStatusMap.set(4, "已上线");
      processStatusMap.set(5, "已下线");
      processStatusMap.set(6, "已禁用");

      let processStatusColorMap = new Map;

      processStatusColorMap.set(1, "pink");
      processStatusColorMap.set(2, "red");
      processStatusColorMap.set(3, "orange");
      processStatusColorMap.set(4, "green");
      processStatusColorMap.set(5, "cyan");
      processStatusColorMap.set(6, "blue");


      let auditStatusMap = new Map;

      auditStatusMap.set(1, "未处理");
      auditStatusMap.set(2, "未通过");
      auditStatusMap.set(3, "已通过");

      let auditStatusColorMap = new Map;

      auditStatusColorMap.set("未处理", "warning");
      auditStatusColorMap.set("未通过", "error");
      auditStatusColorMap.set("已通过", "success");

      const edit = (btId: number) => {
        console.log("拿到的数据：", btId);

        axios.get('baseTag/editBaseTag/' + btId).then((response) => {
          const data = response.data;
          if (data.success) {

            handleQueryTags();


          } else {
            message.error(data.message);
          }
        });

      };

      const handleban = (record: any) => {
        console.log("拿到的数据：", record.btId);

        if (record.processStatus !== 4 ) {
          message.error('下你个头！！');
        }  else
        {
          axios.get('baseTag/banBaseTag/' + record.btId).then((response) => {
            const data = response.data;
            if (data.success) {

              handleQueryTags();


            } else {
              message.error(data.message);
            }
          });
        }

      };

      const online = (record: any) => {
        console.log("拿到的数据：", record.btId);

        if (record.processStatus === 6) {
          message.error('办不了，谁让你把标签禁用的！！');
        } else if (record.processStatus === 2) {
          message.error('干什么 干什么 还在开发呢');
        } else if (record.processStatus === 3 || record.processStatus === 5 )
        {
          axios.get('baseTag/onlineBaseTag/' + record.btId).then((response) => {
            const data = response.data;
            if (data.success) {

              handleQueryTags();
              message.success(data.content);

            } else {
              message.error(data.message);
            }
          });
        }else {
          message.error('不想理你~');
        }

      };

      const offline = (btId: number) => {
        console.log("拿到的数据：", btId);

        axios.get('baseTag/offlineBaseTag/' + btId).then((response) => {
          const data = response.data;
          if (data.success) {

            handleQueryTags();


          } else {
            message.error(data.message);
          }
        });

      };

      const param = ref({
        tagName: '',
      });




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

        const  data1: any = [
          {
            name: '人口属性',
            children: [{
              name: '性别',
              value: 1,
            }, {
              name: '年龄段',
              value: 1,
            }, {
              name: '身高',
              value: 1,
            }, {
              name: '民族',
              value: 1,
            }, {
              name: '籍贯',
              value: 1,
            }, {
              name: '政治面貌',
              value: 1,
            }, {
              name: '职业',
              value: 1,
            }, {
              name: '婚姻状况',
              value: 1,
            }, {
              name: '学历',
              value: 1,
            }, {
              name: '就业状况',
              value: 1,
            }, {
              name: '星座',
              value: 1,
            }, {
              name: '所在商圈',
              value: 1,
            }, {
              name: '国籍',
              value: 1,
            }

            ]
          },
          {
            name: '行为属性',
            children: [{
              name: '最近登录',
              value: 1,
            }, {
              name: '浏览页面',
              value: 1,
            }, {
              name: '浏览时长',
              value: 1,
            }, {
              name: '访问频率',
              value: 1,
            }, {
              name: '设备类型',
              value: 1,
            }, {
              name: '浏览时段',
              value: 1,
            }, {
              name: '登录频率',
              value: 1,
            }, {
              name: '浏览商品',
              value: 1,
            }, {
              name: '购买商品',
              value: 1,
            }, {
              name: '商品偏好',
              value: 1,
            }, {
              name: '品类偏好',
              value: 1,
            }, {
              name: '品牌偏好',
              value: 1,
            }

            ]
          },
          {
            name: '商业属性',
            children: [{
              name: '消费周期',
              value: 1,
            }, {
              name: '消费能力',
              value: 1,
            }, {
              name: '客单价',
              value: 1,
            }, {
              name: '支付方式',
              value: 1,
            }, {
              name: '单笔最高',
              value: 1,
            }, {
              name: '购买频率',
              value: 1,
            }, {
              name: '退货率',
              value: 1,
            }, {
              name: '换货率',
              value: 1,
            }, {
              name: '省钱能手',
              value: 1,
            }, {
              name: '有券必买',
              value: 1,
            }, {
              name: '客服咨询频率',
              value: 1,
            }
            ]
          },
          {
            name: '用户价值',
            children: [{
              name: '房产',
              value: 2,
            },{
              name: '房产价值',
              value: 2,
            },{
              name: '车产',
              value: 2,
            },{
              name: '车产价值',
              value: 2,
            }
            ]
          }
        ];
        const itemStyle: any = {
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

        const data: any = [
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
            let block: any = level1[i].children;
            let bookScore: any = [];
            let bookScoreId = 0;
            for (var star = 0; star < block.length; ++star) {
              var style = (function (name: string) {
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

                block[star].children.forEach(function (book: any) {
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
                  } else {
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

        let option1 = {
          visualMap: {
            type: 'continuous',
            min: 0,
            max: 10,
            inRange: {
              color: ['#2F93C8', '#AEC48F', '#FFDB5C', '#F98862']
            }
          },
          series: {
            type: 'sunburst',
            data: data1,
            radius: [0, '90%'],
            label: {
              rotate: 'radial'
            }
          }
        };

        let option = {
          backgroundColor: bgColor,
          color: colors,
          series: [{
            type: 'sunburst',
            center: ['50%', '48%'],
            data: data,
            sort: function (a: any, b: any) {
              if (a.depth === 1) {
                return b.getValue() - a.getValue();
              } else {
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
        myChart.setOption(option1);
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

      // const baseTags = ref();
      // baseTags.value = {};

      // const baseTagsTree = ref([])
      const baseTagsTree = ref();
      baseTagsTree.value = [];

      const handleQueryBaseTag = () => {
        // axios.get("/baseTag/listBaseTags").then((response) => {
        axios.get("/baseTag/listBaseTagsTreeNodes").then((response) => {
          const data = response.data;

          // console.log(response)

          if (data.success) {
            baseTags.value = data.content;

            baseTagsTree.value = [];
            baseTagsTree.value = Tool.array2Tree(baseTags.value, 10);

            console.log("baseTagTree",baseTagsTree.value)

            // alert(JSON.stringify(baseTagsTree));

          } else {
            message.error(data.message);
          }

        });

      };

      const baseTags = ref<TagItem[]>();
      baseTags.value = [];

      const baseTagsAudit = ref<TagItem[]>();
      baseTagsAudit.value = [];

      const handleQueryTags = () => {


        axios.get('baseTag/listBaseTags').then((response) => {
          const data = response.data;
          if (data.success) {
            message.success("获取标签数据成功！");
            baseTags.value = Tool.array2Tree(data.content, 10);
            console.log("baseTag:", baseTags.value);


          } else {
            message.error(data.message);
          }
        });

      };

      const handleClick = (tabName: string) => {

        if(tabName === 'tag-audit'){

          axios.get('/baseTag/listBaseTagsAudit').then((response) => {
            const data = response.data;
            if (data.success) {
              message.success("获取标签数据成功！");
              baseTagsAudit.value = Tool.array2Tree(data.content, 10);
              console.log("baseTagsAudit:", baseTagsAudit.value);


            } else {
              message.error(data.message);
            }
          });


        }


      }


      const activeKey = ref('basetag-statistic')


      //初始化逻辑都写到onMounted()里
      onMounted(() => {
        console.log("onMounted");

        tagStatistic();

        tagTreeStatistic();

        tagStatisticSun();

        handleQueryBaseTag();

        handleQueryTags();

      });

      //html代码要拿到响应式变量 需要在setup的最后return
      return {

        statistic,

        activeKey,

        baseTags,
        columns,
        rowSelection,

        processStatusMap,
        processStatusColorMap,

        edit,
        handleban,
        online,
        offline,

        param,

        handleClick,
        baseTagsAudit,
        columnsAudit,
        rowSelectionAudit,
        auditStatusMap,
        auditStatusColorMap,

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