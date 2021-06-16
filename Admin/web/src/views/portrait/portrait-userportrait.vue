<template>
    <a-layout>

        <h1 class="h1">这是用户画像模块的页面</h1>

        <a-tabs v-model:activeKey="activeKey">
            <a-tab-pane key="user-profile-statistic">
                <template #tab>
        <span @click="handleClick('user-profile-statistic')">
         <icon-font class="icons-bar" type="icon-fenxi" style="font-size: 18px"/>
          画像概览
        </span>
                </template>

                <div style="background-color: #ececec; padding: 20px">
                    <a-row :gutter="16">
                        <a-col :span="8">
                            <a-card title="总用户数" :bordered="false">
                                <p>card content</p>
                            </a-card>
                        </a-col>
                        <a-col :span="8">
                            <a-card title="成交额" :bordered="false">
                                <p>card content</p>
                            </a-card>
                        </a-col>
                        <a-col :span="8">
                            <a-card title="Card title" :bordered="false">
                                <p>card content</p>
                            </a-card>
                        </a-col>
                    </a-row>
                </div>

                <a-row>
                    <a-col :span="11" id="main-col">
                        <div id="tag-statistic-sun" style="width:400px;height:600px;"></div>
                    </a-col>

                    <a-col :span="11" id="main-col2">
                        <div id="tag-statistic" style="width:400px;height:600px;"></div>
                    </a-col>

                </a-row>

                <!--        <a-row>-->
                <!--          <a-col :span="24" id="main-col">-->
                <!--            <div id="tag-statistic" style="width:50%;height:600px;"></div>-->
                <!--          </a-col>-->
                <!--        </a-row>-->

                <a-row>
                    <a-col :span="24" id="main-col">
                        <div id="main2" style="width:100%;height:800px;"></div>
                    </a-col>
                </a-row>

            </a-tab-pane>
            <a-tab-pane key="user-profile-search">
                <template #tab>
        <span @click="handleClick('user-profile-search')">
          <icon-font class="icons-bar" type="icon-jiansuo" style="font-size: 18px"/>
          个人画像检索
        </span>
                </template>
                检索条件

                <!--        <transition-->
                <!--                enter-active-class="animate__animated animate__fadeIn"-->
                <!--                leave-active-class="animate__animated animate__fadeOut"-->
                <!--        >-->
                <!--          <div class="fixedSearch" >-->
                <!--              <a-input-search-->
                <!--                      v-model:value="value"-->
                <!--                      placeholder="请输入检索条件"-->
                <!--                      enter-button-->
                <!--                      @search="onSearch"-->
                <!--              />-->

                <br>

                <div style="margin-bottom: 16px; width: 600px; position: center;">
                    <a-input v-model:value="userSearchValue" placeholder="请输入检索条件，回车搜索..."
                             @pressEnter="onSearch">
                        <template #addonBefore>
                            <a-select v-model:value="userSearchKey" style="width: 111px">
                                <a-select-option value="telNum">电话号码</a-select-option>
                                <a-select-option value="userName">用户名</a-select-option>
                            </a-select>
                        </template>
                    </a-input>
                </div>

                <a-divider></a-divider>

                <div class="post-area">
                    <div class="container">
                        <div class="row">
                            <div class="single-post">
                                <div class="inner-post">
                                    <div class="post-img">
                                        <a href="#"><img src="/img/userprofile/popAttr.png" alt="blog"></a>
                                    </div>
                                    <div class="post-info">
                                        <div class="post-title">
                                            <h3><a href="#">人口属性 · 用户特征</a></h3>
                                        </div>
                                        <div class="post-content">
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting
                                                industry. Lorem Ipsum has been the industry's standard dum my text ever
                                                since the when an unknown printer took a galley of type and scrambled it
                                                to make aspecimen book. It has survived not only five centuries, but
                                                also the leap into typesetting, remaining essentially </p>
                                        </div>
                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                    <li><span class="flaticon-man-user user"></span>
                                                        <p>By <a href="#">Admin</a></p></li>
                                                    <li><span class="flaticon-calendar clendar"></span>
                                                        <p>20 Jan 2018</p></li>
                                                </ul>
                                            </div>
                                            <div class="post-readmore pull-right">
                                                <a href="#" class="readmore-btn">Read More <span>+</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-date one">
                                    <span>01</span>
                                </div>
                            </div>
                            <div class="single-post">
                                <div class="inner-post">
                                    <div class="post-img">
                                        <a href="#"><img src="/img/userprofile/commercial.png" alt="blog"></a>
                                    </div>
                                    <div class="post-info">
                                        <div class="post-title">
                                            <h3><a href="#">商业属性 · 消费特征</a></h3>
                                        </div>
                                        <div class="post-content">
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting
                                                industry. Lorem Ipsum has been the industry's standard dum my text ever
                                                since the when an unknown printer took a galley of type and scrambled it
                                                to make aspecimen book. It has survived not only five centuries, but
                                                also the leap into typesetting, remaining essentially </p>
                                        </div>
                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                    <li><span class="flaticon-man-user user"></span>
                                                        <p>By <a href="#">Admin</a></p></li>
                                                    <li><span class="flaticon-calendar clendar"></span>
                                                        <p>20 Jan 2018</p></li>
                                                </ul>
                                            </div>
                                            <div class="post-readmore pull-right">
                                                <a href="#" class="readmore-btn">Read More <span>+</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-date two">
                                    <span>02</span>
                                </div>
                            </div>
                            <div class="single-post">
                                <div class="inner-post">
                                    <div class="post-img">
                                        <a href="#"><img src="/img/userprofile/behaveAttr.png" alt="blog"></a>
                                    </div>
                                    <div class="post-info">
                                        <div class="post-title">
                                            <h3><a href="#">行为属性 · 兴趣特征</a></h3>
                                        </div>
                                        <div class="post-content">
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting
                                                industry. Lorem Ipsum has been the industry's standard dum my text ever
                                                since the when an unknown printer took a galley of type and scrambled it
                                                to make aspecimen book. It has survived not only five centuries, but
                                                also the leap into typesetting, remaining essentially </p>
                                        </div>
                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                    <li><span class="flaticon-man-user user"></span>
                                                        <p>By <a href="#">Admin</a></p></li>
                                                    <li><span class="flaticon-calendar clendar"></span>
                                                        <p>20 Jan 2018</p></li>
                                                </ul>
                                            </div>
                                            <div class="post-readmore pull-right">
                                                <a href="#" class="readmore-btn">Read More <span>+</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-date three">
                                    <span>03</span>
                                </div>
                            </div>
                            <div class="single-post">
                                <div class="inner-post">
                                    <div class="post-img">
                                        <a href="#"><img src="/img/userprofile/userValueAttr.png" alt="blog"></a>
                                    </div>
                                    <div class="post-info">
                                        <div class="post-title">
                                            <h3><a href="#">用户价值</a></h3>
                                        </div>
                                        <div class="post-content">
                                            <p>Lorem Ipsum is simply dummy text of the printing and typesetting
                                                industry. Lorem Ipsum has been the industry's standard dum my text ever
                                                since the when an unknown printer took a galley of type and scrambled it
                                                to make aspecimen book. It has survived not only five centuries, but
                                                also the leap into typesetting, remaining essentially </p>
                                        </div>
                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                    <li><span class="flaticon-man-user user"></span>
                                                        <p>By <a href="#">Admin</a></p></li>
                                                    <li><span class="flaticon-calendar clendar"></span>
                                                        <p>20 Jan 2018</p></li>
                                                </ul>
                                            </div>
                                            <div class="post-readmore pull-right">
                                                <a href="#" class="readmore-btn">Read More <span>+</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-date four">
                                    <span>04</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <!--          </div>-->

                <!--          <a-input-search-->
                <!--                  v-model:value="value"-->
                <!--                  placeholder="input search text"-->
                <!--                  enter-button-->
                <!--                  @search="onSearch"-->
                <!--          />-->

                <!--        </transition>-->


            </a-tab-pane>
            <a-tab-pane key="user-profile-group-search">
                <template #tab>
        <span @click="handleClick('user-profile-search')">
          <icon-font class="icons-bar" type="icon-renyingjiansuo" style="font-size: 18px"/>
          群像检索
        </span>
                </template>
                检索条件
            </a-tab-pane>
        </a-tabs>

    </a-layout>
</template>


<script lang="ts">
    import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

    import axios from 'axios';

    import {createFromIconfontCN} from "@ant-design/icons-vue/es/index";
    import store from "@/store";

    import {message} from 'ant-design-vue';

    const IconFont = createFromIconfontCN({
        scriptUrl: '//at.alicdn.com/t/font_2614026_czwi4xllqlp.js',
    });

    import {groupItems} from './data/tagdata';

    declare let echarts: any;


    export default defineComponent({
        name: 'PortraitUserPortrait',

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
                        let block: any = level1[i].children;
                        let bookScore: any = [];
                        let bookScoreId = 0;
                        for (var star = 0; star < block.length; ++star) {
                            var style = (function (name: string) {
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
                myChart.setOption(option);
            };


            const activeKey = ref('user-profile-statistic');

            const userSearchKey = ref('电话号码');
            const userSearchValue = ref('');

            const userProfile = ref();
            userProfile.value = {};


            const onSearch = (searchValue: string) => {
                console.log('use value', searchValue);
                console.log('or use this.value', userSearchValue.value);

                axios.get("user-profile/searchByTel/" + userSearchValue.value).then((response) => {
                    const data = response.data;
                    if (data.success) {
                        userProfile.value = data.content;
                        alert(userProfile.value);
                        console.log(userProfile.value);
                    } else {
                        message.error(data.message);
                    }
                });

            };

            const handleClick = (tabValue: string) => {
                console.log('use value', tabValue);
                console.log('or use this.value', userSearchValue.value);
            };


            //初始化逻辑都写到onMounted()里
            onMounted(() => {
                console.log("onMounted");

                tagStatistic();

                tagTreeStatistic();

                tagStatisticSun();

                // axios.get("/demo/list").then((response) => {
                //   const data = response.data;
                //   //ref数据的赋值
                //
                //
                //   // console.log(response)
                // });
            });

            //html代码要拿到响应式变量 需要在setup的最后return
            return {

                statistic,

                activeKey,

                userSearchValue,
                userSearchKey,
                onSearch,

                userProfile,

                handleClick,

                pagination: {
                    onChange: (page: any) => {
                        console.log(page);
                    },
                    pageSize: 3,
                },
                actions: [
                    {type: 'StarOutlined', text: '156'},
                    {type: 'LikeOutlined', text: '156'},
                    {type: 'MessageOutlined', text: '2'},
                ],
            }
        },


        components: {
            IconFont,
        },

    });
</script>


<style scoped>

    .h1 {
        font-size: 25px;
    }


    .fixedSearch {
        position: fixed;
        width: 200px;
        z-index: 999;
        background: #f6f6f6;
        height: 120px;
        overflow: hidden;
    }

    .searchBox {
        position: absolute;
        top: -100px;
        left: 50%;
        transform: translateX(-50%);
    }

    .box100 {
        height: 100px;
        background: #3a8ee6;
        border: 1px solid black;
    }


    /*  ================ container&row  ====================*/

    .container {
        margin-right: auto;
        margin-left: auto;
        padding-left: 15px;
        padding-right: 15px;
    }

    .container:before, .container:after {
        content: " ";
        display: table;
    }

    .container:after {
        clear: both;
    }

    @media (min-width: 768px) {
        .container {
            width: 750px;
        }
    }

    @media (min-width: 992px) {
        .container {
            width: 970px;
        }
    }

    @media (min-width: 1200px) {
        .container {
            width: 1170px;
        }
    }

    .container-fluid {
        margin-right: auto;
        margin-left: auto;
        padding-left: 15px;
        padding-right: 15px;
    }

    .container-fluid:before, .container-fluid:after {
        content: " ";
        display: table;
    }

    .container-fluid:after {
        clear: both;
    }

    .row {
        margin-left: -15px;
        margin-right: -15px;
    }

    .row:before, .row:after {
        content: " ";
        display: table;
    }

    .row:after {
        clear: both;
    }


    /*=================================================
        7. post-area
    ==============================================================*/
    .post-area {
        padding-top: 115px;
        padding-bottom: 110px;
        position: relative;
    }

    .post-area .single-post {
        position: relative;
        margin-bottom: 70px;
    }

    .post-area .single-post:hover .post-date span {
        background: #665cf2;
    }

    .post-area .single-post:hover .post-date.two span {
        background: #e44a4b;
    }

    .post-area .single-post:hover .post-date.three span {
        background: #31a041;
    }

    .post-area .single-post:hover .post-date.four span {
        background: #ffab08;
    }

    .post-area .single-post:hover .post-date.five span {
        background: #15a59e;
    }

    .post-area .single-post .inner-post {
        padding: 40px;
        -webkit-box-shadow: 0 0 40px #dcdbff;
        -o-box-shadow: 0 0 40px #dcdbff;
        -moz-box-shadow: 0 0 40px #dcdbff;
        -ms-box-shadow: 0 0 40px #dcdbff;
        box-shadow: 0 0 40px #dcdbff;
        overflow: hidden;
    }

    .post-area .single-post .inner-post .post-img {
        float: left;
        width: 30%;
    }

    .post-area .single-post .inner-post .post-img img {
        width: 100%;
        height: 100%;
    }

    .post-area .single-post .inner-post .post-info {
        float: left;
        width: 70%;
        padding-left: 30px;
    }

    .post-area .single-post .inner-post .post-info .post-title h3 {
        font-size: 24px;
        line-height: 26px;
        font-weight: 600;
        margin-top: 10px;
        margin-bottom: 18px;
    }

    .post-area .single-post .inner-post .post-info .post-title h3 a {
        color: #333333;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s;
    }

    .post-area .single-post .inner-post .post-info .post-title h3 a:hover {
        color: #6c62ff;
    }

    .post-area .single-post .inner-post .post-info .post-content p {
        font-size: 16px;
        color: #333333;
        line-height: 26px;
        margin-bottom: 25px;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li {
        display: inline-block;
        margin-right: 30px;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span {
        display: inline-block;
        width: 25px;
        height: 25px;
        line-height: 25px;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        -ms-border-radius: 50%;
        -o-border-radius: 50%;
        border-radius: 50%;
        background: #dad8ff;
        text-align: center;
        margin-right: 5px;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span.clendar {
        background: #ffd7d7;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span.clendar:before {
        color: #fe6061;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span.user:before {
        color: #6c62ff;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span::before {
        margin-left: 0px;
        font-size: 14px;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li p {
        margin: 0;
        display: inline-block;
        font-size: 18px;
        color: #333333;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li p a {
        color: #333333;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li p a:hover {
        color: #6c62ff;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .post-readmore .readmore-btn {
        display: inline-block;
        font-size: 18px;
        color: #333;
        font-weight: 500;
        text-transform: capitalize;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .post-readmore .readmore-btn span {
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s;
        display: inline-block;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .post-readmore .readmore-btn:hover {
        color: #6c62ff;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .post-readmore .readmore-btn:hover span {
        color: #6c62ff;
        -webkit-transform: rotate(360deg);
        -o-transform: rotate(360deg);
        -moz-transform: rotate(360deg);
        -ms-transform: rotate(360deg);
        transform: rotate(360deg);
    }

    .post-area .single-post .post-date {
        position: absolute;
        right: 0;
        top: 0;
    }

    .post-area .single-post .post-date.two span {
        background: #fe6566;
    }

    .post-area .single-post .post-date.three span {
        background: #3fb750;
    }

    .post-area .single-post .post-date.four span {
        background: #ff7008;
    }

    .post-area .single-post .post-date.five span {
        background: #23bdb6;
    }

    .post-area .single-post .post-date span {
        width: 100px;
        height: 90px;
        background: #7167ff;
        color: #ffffff;
        font-size: 40px;
        font-weight: 500;
        display: inline-block;
        text-align: center;
        padding: 15px 0;
        padding-left: 20px;
        -webkit-border-radius: 0px 0px 0px 100px;
        -moz-border-radius: 0px 0px 0px 100px;
        -ms-border-radius: 0px 0px 0px 100px;
        -o-border-radius: 0px 0px 0px 100px;
        border-radius: 0px 0px 0px 100px;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s;
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