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

<!--                <a-row>-->
<!--                    <a-col :span="24" id="commercial-statistic-radar-col">-->
<!--                        <div id="commercial-statistic-radar" style="width:400px;height:800px;"></div>-->
<!--                    </a-col>-->
<!--                </a-row>-->

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

                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                  <li><span class="flaticon-user user"></span>
                                                    <a href="#" style="font-size: 20px;">性别 </a><p style="font-size: 30px;">{{ userProfile.gender }}</p>
                                                  </li>
                                                  <li><span class="flaticon-calendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">年龄段 </a><p style="font-size: 30px;">{{ userProfile.ageGroup }}后</p>
                                                  </li>
                                                    <li><span class="flaticon-man-user user"></span>
                                                      <a href="#" style="font-size: 20px;">身高 </a><p style="font-size: 30px;">170-179cm</p>
                                                    </li>
                                                    <li><span class="flaticon-calendar clendar"></span>
                                                      <a href="#" style="font-size: 20px;">民族 </a><p style="font-size: 30px;">汉族</p>
                                                    </li>
                                                  <li><span class="flaticon-man-clendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">籍贯 </a><p style="font-size: 30px;">成都</p>
                                                  </li>
                                                  <li><span class="flaticon-man-user user"></span>
                                                    <a href="#" style="font-size: 20px;">政治面貌 </a><p style="font-size: 30px;">{{ userProfile.politicalFace }}</p>
                                                  </li>
                                                  <li><span class="flaticon-man-clendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">职业 </a><p style="font-size: 30px;">{{ userProfile.job }}</p>
                                                  </li>
                                                  <li><span class="flaticon-man-user user"></span>
                                                    <a href="#" style="font-size: 20px;">婚姻状况 </a><p style="font-size: 30px;">{{ userProfile.marriage }}</p>
                                                  </li>
                                                  <li><span class="flaticon-man-clendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">学历 </a><p style="font-size: 30px;">本科</p>
                                                  </li>
                                                  <li><span class="flaticon-man-user user"></span>
                                                    <a href="#" style="font-size: 20px;">就业状况 </a><p style="font-size: 30px;">事业单位</p>
                                                  </li>
                                                  <li><span class="flaticon-man-clendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">星座 </a><p style="font-size: 30px;">
                                                      {{ userProfile.Constellation }}</p>
                                                  </li>
                                                  <li><span class="flaticon-man-user user"></span>
                                                    <a href="#" style="font-size: 20px;">所在商圈 </a><p style="font-size: 30px;">王府井</p>
                                                  </li>
                                                  <li><span class="flaticon-man-clendar clendar"></span>
                                                    <a href="#" style="font-size: 20px;">国籍 </a><p style="font-size: 30px;">
                                                      {{ userProfile.nationality }}</p>
                                                  </li>


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

                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <ul>
                                                    <li><span class="flaticon-user commercial-head"></span>
                                                        <a href="#" style="font-size: 20px;">消费周期 </a><p style="font-size: 30px;">7日</p>
                                                    </li>
                                                    <li><span class="flaticon-calendar commercial-tail"></span>
                                                        <a href="#" style="font-size: 20px;">客单价 </a><p style="font-size: 30px;">1-999</p>
                                                    </li>
                                                    <li><span class="flaticon-man-user commercial-head"></span>
                                                        <a href="#" style="font-size: 20px;">最喜欢的支付方式 </a><p style="font-size: 30px;">{{ userProfile.paymentCode }}</p>
                                                    </li>
                                                    <li><span class="flaticon-calendar commercial-tail"></span>
                                                        <a href="#" style="font-size: 20px;">单笔最高 </a><p style="font-size: 30px;">1-999</p>
                                                    </li>
                                                    <li><span class="flaticon-man-clendar commercial-head"></span>
                                                        <a href="#" style="font-size: 20px;">省钱能手 </a><p style="font-size: 30px;">5折-7折</p>
                                                    </li>
                                                    <li><span class="flaticon-man-user commercial-tail"></span>
                                                        <a href="#" style="font-size: 20px;">有券必买 </a><p style="font-size: 30px;">折扣券</p>
                                                    </li>
                                                    <li><span class="flaticon-man-clendar commercial-head"></span>
                                                        <a href="#" style="font-size: 20px;">消费能力 </a><p style="font-size: 30px;">{{ userProfile.spendPower }}</p>
                                                    </li>

                                                </ul>

                                            </div>

                                          <div class="commercial-radar" style="padding-left: 100px;">
                                              <a-row>
                                                  <a-col :span="24" id="commercial-statistic-radar-col">
                                                      <div id="commercial-statistic-radar" style="width:490px;height:400px;"></div>
                                                  </a-col>
                                              </a-row>
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

                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">

                                                    <p class="behavior-item" style="font-size: 20px; color: #61ac85;">
                                                        <icon-font class="icons-bar" type="icon-denglu" style="font-size: 18px"/>
                                                访问状态 </p>

                                                <div class="contact-info">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-telephone">
                                                                            <icon-font class="icons-bar" type="icon-huabanfuben" style="font-size: 18px"/>
                                                                        </span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>最近登录</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>{{ userProfile.RecentLogin }}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>设备类型</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>@{{ userProfile.DeviceType }}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>登录频率</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>较少</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                                <p class="behavior-item" style="font-size: 20px; color: #57c3c2;">
                                                    <icon-font class="icons-bar" type="icon-liulan" style="font-size: 18px"/>
                                                    浏览轨迹 </p>

                                                <div class="contact-info">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-telephone">
                                                                            <icon-font class="icons-bar" type="icon-huabanfuben" style="font-size: 18px"/>
                                                                        </span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>浏览页面 · 时长</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>{{ userProfile.BrowsePage }} -- 5分钟以上</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>浏览时段</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>@{{ userProfile.log_time }}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>浏览商品</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>较少</p>
                                                                        </div>

                                                                        <div class="user-BP-wordCloud" style="padding-left: 100px;">
                                                                            <a-row>
                                                                                <a-col :span="24" id="commercial-statistic-radar-col">
                                                                                    <div id="user-browse-product-wordCloud" style="width:520px;height:400px;"></div>
                                                                                </a-col>
                                                                            </a-row>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                                <p class="behavior-item" style="font-size: 20px; color: #29b7cb;">
                                                    <icon-font class="icons-bar" type="icon-fengxianpianhaoceshi" style="font-size: 18px"/>
                                                    偏好分析 </p>


                                                <div class="contact-info">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-telephone"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>商品偏好</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>所有商品</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="single-info box-shadow">
                                                                <div class="info-iiner">
                                                                    <div class="info-incon">
                                                                        <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                    </div>
                                                                    <div class="info-content">
                                                                        <div class="contact-info-title">
                                                                            <h5>品类偏好</h5>
                                                                        </div>
                                                                        <div class="contact-info-content">
                                                                            <p>所有品类</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="single-info box-shadow">
                                                            <div class="info-iiner">
                                                                <div class="info-incon">
                                                                    <span class="flaticon-black-back-closed-envelope-shape"></span>
                                                                </div>
                                                                <div class="info-content">
                                                                    <div class="contact-info-title">
                                                                        <h5>品牌偏好</h5>
                                                                    </div>
                                                                    <div class="contact-info-content">
                                                                        <p>海尔 卡萨帝 摩卡 小超人 统帅</p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>



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
                                            <p> </p>
                                        </div>
                                        <div class="blog-meta fix">
                                            <div class="meta-left pull-left">
                                                <a-list :grid="{ gutter: 16, column: 4 }" :data-source="userValueData">
                                                    <template #renderItem="{ item }">
                                                        <a-list-item>
                                                            <a-card :title="item.title">
                                                                <p style="font-weight: bolder;">{{ item.value }}</p>
                                                            </a-card>
                                                        </a-list-item>
                                                    </template>
                                                </a-list>
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
        scriptUrl: '//at.alicdn.com/t/font_2614026_u9y8k012thc.js',
    });

    import {groupItems} from './data/tagdata';

    declare let echarts: any;

    interface DataItem {
        title: string;
        value: string;
    }
    const userValueData: DataItem[] = [
        {
            title: '房产',
            value: '商业贷款',
        },
        {
            title: '房产价值',
            value: '50-100w以内',
        },
        {
            title: '车产',
            value: '有车有贷',
        },
        {
            title: '车产价值',
            value: '10-20w以内',
        },
    ];

    interface UBPWordCloud {
        name: string;
        value: number;
    }





    export default defineComponent({
        name: 'PortraitUserPortrait',

        //放一些参数定义，方法定义
        setup() {
            console.log("setup");

            let paymentMap = new Map();
            paymentMap.set("cdd","货到付款");
            paymentMap.set("wx","微信支付");
            paymentMap.set("alipay","支付宝");
            paymentMap.set("card","银行卡");
            paymentMap.set("other","其他");



          var items = ['1','2','4','5','6','7','8','9','10'];
          var item = items[Math.floor(Math.random()*items.length)];

          //身高
          let heights = ['150以下'.repeat(1),'160-164'.repeat(2),'165-168'.repeat(12),'169-171'.repeat(20),'172-174'.repeat(30),'175-177'.repeat(20),'178-181'.repeat(12),'182-186'.repeat(2),'187以上'.repeat(1)];
          let randomHeight = heights[Math.floor(Math.random()*heights.length)];




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

            const commercialStatisticPolar = () => {
                // 基于准备好的dom，初始化echarts实例
                const myChart = echarts.init(document.getElementById('commercial-statistic-polar'));

                // 指定图表的配置项和数据

                // let option = {
                //   xAxis: {
                //     type: 'category',
                //     data: ['消费能力', '购买频率', '退货率', '换货率', '客服咨询频率']
                //   },
                //   yAxis: {
                //     type: 'value'
                //   },
                //   series: [{
                //     data: ['中上', '高', '低', '低', '低'],
                //     type: 'bar',
                //     showBackground: true,
                //     backgroundStyle: {
                //       color: 'rgba(180, 180, 180, 0.2)'
                //     }
                //   }]
                // };

              let option = {
                xAxis: {
                  type: 'category',
                  data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                  type: 'value'
                },
                series: [{
                  data: [120, 200, 150, 80, 70, 110, 130],
                  type: 'bar',
                  showBackground: true,
                  backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                  }
                }]
              };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            };

            const commercialStatisticRadar = () => {
                // 基于准备好的dom，初始化echarts实例
                const myChart = echarts.init(document.getElementById('commercial-statistic-radar'));

                // 指定图表的配置项和数据

                // 消费能力 购买频率 退货率 换货率 客服咨询频率
                // let data = ['高','高','低','低','中'];
                let dataBJ = [
                    [6,2,2,2],
                ];

                let lineStyle = {
                    normal: {
                        width: 2,
                        opacity: 0.9
                    }
                };

                let option = {
                    backgroundColor: '#fff',
                    title: {
                        text: 'AQI - 雷达图',
                        left: 'center',
                        textStyle: {
                            color: '#000'
                        }
                    },
                    legend: {
                        bottom: 5,
                        data: ['商业属性-消费特征',],
                        itemGap: 20,
                        textStyle: {
                            color: '#89C6F2',
                            fontSize: 16
                        },
                        selectedMode: 'single'
                    },
                    visualMap: {
                        show: true,
                        min: 0,
                        max: 20,
                        dimension: 4,
                        inRange: {
                            colorLightness: [0.5, 0.8]
                        }
                    },
                    radar: {
                        indicator: [
                            {name: '购买频率', max: 7},
                            {name: '退货率', max: 7},
                            {name: '换货率', max: 7},
                            {name: '客服咨询频率', max: 7},
                        ],
                        shape: 'circle',
                        splitNumber: 5,
                        name: {
                            textStyle: {
                                color: 'rgb(238, 197, 102)'
                            }
                        },
                        splitLine: {
                            lineStyle: {

                                color: [
                                    'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                                    'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                                    'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                                ].reverse()
                            }
                        },
                        splitArea: {
                            show: true
                        },
                        axisLine: {
                            lineStyle: {

                                color: 'rgba(238, 197, 102, 0.5)'
                            }
                        }
                    },
                    series: [
                        {
                            name: '商业属性-消费特征',
                            type: 'radar',
                            lineStyle: lineStyle,
                            data: dataBJ,
                            symbol: 'none',
                            itemStyle: {
                                color: '#F9713C',
                                // fontSize: 25,
                            },
                            areaStyle: {
                                opacity: 0.3
                            }
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            };


            const activeKey = ref('user-profile-statistic');

            const userSearchKey = ref('电话号码');
            const userSearchValue = ref('');

            let userBrowseProductWD: UBPWordCloud[]  = [];

            const userProfile = ref();
            // userProfile.value = {
            //   "gender": "女",
            //   "RecentLogin": "超过30日",
            //   "highestPay": "99.00",
            //   "mobile": "13306834911",
            //   "ageGroup": "90",
            //   "userName": "督咏",
            //   "BrowseProduct": "电饭煲,滤芯,冷柜,4K电视,Haier/海尔冰箱,燃气热水器,微波炉,前置过滤器,电磁炉,烤箱,电风扇,电热水器,燃气灶,Leader/统帅冰箱,净水机,吸尘器/除螨仪,烟灶套系,嵌入式厨电,取暖电器,破壁机,挂烫机,LED电视,波轮洗衣机,电水壶/热水瓶,空气净化器,其他,料理机,冰吧,智能电视",
            //   "log_time": "8时--10时",
            //   "politicalFace": "群众",
            //   "powerOfConsumption": "很低",
            //   "DeviceType": "Windows",
            //   "ConsumptionCycle": "2周",
            //   "moneySaver": "8折-9折",
            //   "paymentCode": "alipay",
            //   "nationality": "中国大陆",
            //   "spendPower": "超高",
            //   "Constellation": "双子座",
            //   "marriage": "已婚",
            //   "BrowsePage": "商品页",
            //   "id": "1",
            //   "job": "军人",
            //   "email": "wcfr817e@yeah.net"
            // };
            userProfile.value = {
              paymentCode: "alipay",
                spendPower: "中",
                BrowseProduct: ['',],
            };

            const userBrowseProductWordCloud = () => {
                // 基于准备好的dom，初始化echarts实例
                const myWordCloud = echarts.init(document.getElementById('user-browse-product-wordCloud'));

                // 指定图表的配置项和数据
                const option ={
                    title: {
                        text: 'Ta喜欢看',
                        x:"center"
                    },

                    backgroundColor:"#c6fbff",//tooltip: {//pointFormat: "{series.name}: {point.percentage:.1f}%"//},
                    series: [
                        {
                            type:"wordCloud",//用来调整词之间的距离
                            gridSize: 10,//用来调整字的大小范围//Text size range which the value in data will be mapped to.//Default to have minimum 12px and maximum 60px size.
                            sizeRange: [12, 40],//Text rotation range and step in degree. Text will be rotated randomly in range [-90, 90] by rotationStep 45//用来调整词的旋转方向，，[0,0]--表明着没有角度，也就是词为水平方向，须要设置角度参考注释内容//rotationRange: [-45, 0, 45, 90],//rotationRange: [ 0,90],
                            rotationRange: [0, 0],//随机生成字体颜色//maskImage: maskImage,
                            textStyle: {
                                normal: {
                                    color: function() {return("rgb(" +Math.round(Math.random()* 255) +

                                        "," +Math.round(Math.random()* 255) +

                                        "," +Math.round(Math.random()* 255) +

                                        ")");

                                    }
                                }
                            },
                            //位置相关设置//Folllowing left/top/width/height/right/bottom are used for positioning the word cloud//Default to be put in the center and has 75% x 80% size.
                            left: "center",
                            top:"center",
                            right:null,
                            bottom:null,
                            width:"400px",
                            height:"400px",//数据
                            data: userBrowseProductWD,
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myWordCloud.setOption(option);
            };


            const onSearch = (searchValue: string) => {
                console.log('use value', searchValue);
                console.log('or use this.value', userSearchValue.value);

                axios.get("user-profile/searchByTel/" + userSearchValue.value).then((response) => {
                    const data = response.data;
                    if (data.success) {
                        userProfile.value = data.content;
                        // alert(userProfile.value);
                        console.log(userProfile.value);

                        userProfile.value.paymentCode = paymentMap.has(userProfile.value.paymentCode) ? paymentMap.get(userProfile.value.paymentCode) : "暂无数据";

                        if (userProfile.value.spendPower === null || userProfile.value.spendPower === undefined || userProfile.value.spendPower === '') {
                            userProfile.value.spendPower = "中";
                        }

                        commercialStatisticRadar();

                        let userBrowseProducts = userProfile.value.BrowseProduct.split(",");
                        // let userBrowseProductWD:any = [];
                        console.log("userBrowseProductWD:",userBrowseProductWD);
                        for(let i=0; i<userBrowseProducts.length; i++){


                            let ubpWordCloud: UBPWordCloud = {
                                name: userBrowseProducts[i],
                                value: Math.floor(Math.random()*userBrowseProducts.length)
                            }

                            userBrowseProductWD.push(ubpWordCloud);

                            // userBrowseProductWD.name = userBrowseProducts[i];
                            // userBrowseProductWD[i].value = Math.floor(Math.random()*userBrowseProducts.length);
                        }

                        console.log("userBrowseProductWD:",userBrowseProductWD);
                        // userBrowseProductWD

                        userBrowseProductWordCloud();

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

              // commercialStatisticPolar();

                // commercialStatisticRadar();

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
              randomHeight,
                paymentMap,

                userValueData,

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


    /*=================================================
    14. contact-area
==============================================================*/
       .contact-info {
        margin-bottom: 20px; }
       .contact-info .single-info {
        padding: 30px; }
       .contact-info .single-info:hover .info-iiner .info-incon span {
        background: #6c62ff; }
       .contact-info .single-info:hover .info-iiner .info-incon span:before {
        color: #ffffff; }
       .contact-info .single-info .info-iiner {
        padding: 10px 20px 15px 20px;
        border: 1px dashed #e2e0ff;
        overflow: hidden; }
       .contact-info .single-info .info-iiner .info-incon {
        float: left;
        margin-top: 18px;
        margin-right: 20px; }
       .contact-info .single-info .info-iiner .info-incon span {
        width: 50px;
        height: 50px;
        line-height: 50px;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        -ms-border-radius: 50%;
        -o-border-radius: 50%;
        border-radius: 50%;
        text-align: center;
        background: #e2e0ff;
        display: inline-block;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s; }
       .contact-info .single-info .info-iiner .info-incon span:before {
        margin-left: 0px;
        color: #6c62ff;
        font-size: 25px;
        -webkit-transition: all 0.5s;
        -o-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        transition: all 0.5s; }
       .contact-info .single-info .info-iiner .info-content {
        float: left; }
       .contact-info .single-info .info-iiner .info-content .contact-info-title h5 {
        font-size: 16px;
        color: #333333;
        font-weight: 700;
        margin-bottom: 5px; }
       .contact-info .single-info .info-iiner .info-content .contact-info-content p {
        margin-bottom: 0px;
        font-size: 16px;
        color: #333333; }







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

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span.commercial-head {
        background: #8076a3;
    }

    .post-area .single-post .inner-post .post-info .blog-meta .meta-left ul li span.commercial-tail {
        background: #f9f4dc;
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


    /*  属性列表  */

    .row {
      display: -ms-flexbox;
      display: flex;
      -ms-flex-wrap: wrap;
      flex-wrap: wrap;
      margin-right: -15px;
      margin-left: -15px;
    }

    .col-5 {
      -ms-flex: 0 0 41.666667%;
      flex: 0 0 41.666667%;
      max-width: 41.666667%;
    }

    .card-stats .icon-big {
      width: 100%;
      height: 100%;
      font-size: 2.2em;
      min-height: 64px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .text-center {
      text-align: center !important;
    }

    .text-warning, .text-warning a {
      color: #ffad46 !important;
    }

    .card-stats .col-stats {
      align-items: center;
      display: flex;
      padding-left: 15px;
    }

    .col-7 {
      -ms-flex: 0 0 58.333333%;
      flex: 0 0 58.333333%;
      max-width: 58.333333%;
    }

    .card-stats .card-category {
      margin-top: 0;
    }
    .card-category {
      margin-top: 8px;
      font-size: 14px;
      color: #8d9498;
      margin-bottom: 0;
      word-break: normal;
    }
    p {
      font-size: 14px;
      line-height: 1.82;
      margin-bottom: 1rem;
      word-break: break-word;
    }

    .card-stats .card-title {
      margin-bottom: 0 !important;
    }
    .card-title {
      margin: 0;
      margin-bottom: 0px;
      color: #575962;
      font-size: 20px;
      font-weight: 400;
      line-height: 1.6;
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