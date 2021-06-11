<template>

  <!--  <div :style="{ bottom: 0, width: '100%', background: '#001529'}">-->
  <!--    <a-layout-footer style="text-align: center; background: #2c3e50">-->
  <!--      ibegu-来个大佬一站式组队平台 @2021-->
  <!--    </a-layout-footer>-->
  <!--  </div>-->
  <!--  <a-layout-footer style="text-align: center; background: #2c3e50; font-size: larger; color: whitesmoke">-->
  <!--            ibegu-来个大佬一站式组队平台 @2021-->
  <!--  </a-layout-footer>-->

  <a-layout-footer style="background: #2c3e50">
    <!--  <div class="footer-wrap">-->
    <!--    <div class="footerO">-->

<!--    <div class="slogan" style="text-align: center;font-size: larger; color: whitesmoke">-->
<!--      ibegu-来个大佬一站式组队平台 @2021-->
<!--    </div>-->
    <div class="common-box footer-box">
      <div class="fl ft-info-box">
        <div class="ft-tit" title="关于赛氪">关于来个大佬</div>
        <ul class="ft-list">
          <li class="item">
            <a href="https://www.saikr.com/about" title="了解赛氪" target="_blank" rel="nofollow">了解来个大佬</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/xyhhr" title="成为校园合伙人" target="_blank" rel="nofollow">成为校园合伙人</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/joinus" title="加入我们" target="_blank" rel="nofollow">加入我们</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/agreement" title="服务协议" target="_blank" rel="nofollow">服务协议</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/news" title="新闻报道" target="_blank" rel="nofollow">新闻报道</a>
          </li>
        </ul>
      </div>
      <div class="fl ft-info-box">
        <div class="ft-tit" title="帮助中心">帮助中心</div>
        <ul class="ft-list">
          <li class="item">
            <a href="https://www.saikr.com/help" title="常见问题" target="_blank" rel="nofollow">常见问题</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/criterion" title="社区规范" target="_blank" rel="nofollow">社区规范</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/products" title="产品介绍" target="_blank" rel="nofollow">产品介绍</a>
          </li>
          <li class="item">
            <a href="https://www.saikr.com/vas" title="增值服务" target="_blank" rel="nofollow">增值服务</a>
          </li>
        </ul>
      </div>
      <div class="fl ft-info-box">
        <div class="ft-tit" title="关注我们">关注我们</div>
        <ul class="ft-list">
          <li class="item pop app">
            <a href="https://www.saikr.com/downloads" title="下载来个大佬APP" target="_blank" rel="nofollow">下载APP</a>
          </li>
          <li class="item pop weixin">
            <a>来个大佬公众号：</a>
          </li>
          <li class="item-tip">(竞赛/竞赛资料)</li>
        </ul>
      </div>
      <div class="fr ft-info-box" style="width: 200px;">
        <div class="ft-tit" title="在线客服">在线客服</div>
        <ul class="ft-list">
          <li class="item">
            来个大佬<span class="item-dot"></span>客服QQ：<a
              href="https://wpa.qq.com/msgrd?v=3&uin=2971529737&site=qq&menu=yes">2971529737</a>
          </li>
          <li class="item">
            来个大佬<span class="item-dot"></span>官方QQ群：
          </li>
          <li class="item pop weixin_service">
            来个大佬<span class="item-dot"></span>客服微信：
          </li>
          <li class="item" style="width:250px;">
            请在工作日10:00-19:00联系客服
          </li>
        </ul>
      </div>
      <div class="ft-partners-box">
        <div class="ft-tit">合作伙伴</div>
        <div class="ft-partners-link-box">
          <a href="https://developer.huawei.com/" target="_blank" title="华为开发者联盟">华为开发者联盟</a>
          <a href="https://developer.huaweiuniversity.com/" target="_blank" title="华为大学">华为大学</a>
          <a href="http://www.ehualu.com/" target="_blank" title="易华录">易华录</a>
          <a href="https://www.boe.com/" target="_blank" title="京东方">京东方</a>
          <a href="http://www.alibabagroup.com" target="_blank" title="阿里巴巴">阿里巴巴</a>
          <a href="http://www.msra.cn" target="_blank" title="微软亚洲研究院">微软亚洲研究院</a>
          <a href="http://click.aliyun.com/m/4719/" target="_blank" title="微软亚洲研究院">阿里云</a>
          <a href="http://click.aliyun.com/m/6632/" target="_blank" title="云栖社区">云栖社区</a>
          <!--  <a href="http://www.coolpad.com" target="_blank" title="酷派">酷派</a>
           <a href="http://www.le.com" target="_blank" title="乐视">乐视</a>
           <a href="https://www.saikr.com/active/chenxingplan" target="_blank" title="晨星成长计划">晨星成长计划</a> -->
          <!-- <a href="http://www.gizwits.com/" target="_blank" title="机智云">机智云</a> -->
        </div>
      </div>
    </div>

    <div class="slogan" style="text-align: center;font-size: larger; color: whitesmoke; margin-top: 20px;">
      ibegu-来个大佬一站式组队平台 @2021
    </div>

    <!--    </div>-->
    <!--  </div>-->
  </a-layout-footer>


</template>

<script lang="ts">
import {defineComponent} from 'vue';

export default defineComponent({
  name: 'admin-footer',
  props: {
    msg: String,
  },
});
</script>

<style scoped>

/*.slogan::after {*/
/*  content: '';*/
/*  display: block;*/
/*  position: absolute;*/
/*  width: 100%;*/
/*  height: 1px;*/
/*  background: #fff;*/
/*  left: 0;*/
/*  bottom: 0;*/

/*}*/

.footer-wrap {
  min-width: 1080px;
  height: 427px;
  margin-top: -427px;
}

* {
  padding: 0;
  padding-top: 0px;
  margin: 0;
  word-break: break-all;
  word-wrap: break-word;
  list-style: none;
  font-size: inherit;

}

.footerO {
  height: 365px;
}

.common-box {
  width: 1080px;
  /*margin: 0 auto;*/
  margin-top: 0px;
  margin-right: auto;
  margin-bottom: 0px;
  margin-left: auto;
}

.footer-box {
  position: relative;
  overflow: hidden;
  overflow-x: hidden;
  overflow-y: hidden;
}

.fl {
  float: left;
}

.ft-info-box {
  width: 185px;
  padding: 40px 40px 0 0;
}

.ft-tit {
  font-size: 20px;
  color: #fff;
  font-weight: 600;
  height: 35px;
  line-height: 1;
  position: relative;
  border-bottom: 1px solid #484747;
}

.ft-tit::after {
  content: '';
  display: block;
  position: absolute;
  width: 100%;
  height: 1px;
  background: #000;
  left: 0;
  bottom: 0;
}

.ft-list .item {
  padding-top: 20px;
  color: #ccc;
  font-size: 14px;
  position: relative;
}

.item a {
  color: #ccc;
}

.fr {
  float: right;
}

.ft-info-box {
  width: 180px;
  padding: 40px 40px 0 0;
}

.ft-partners-box {

  clear: both;
  padding-top: 56px;

}

.ft-partners-link-box a {
  display: inline-block;
  color: #ccc;
  margin-right: 20px;
  font-size: 14px;
}

.item.weixin::after {
  content: '';
  display: inline-block;
  vertical-align: middle;
  width: 18px;
  height: 18px;
  background-image: url(../assets/contact.png);
  background-position: -35px -466px;
  background-repeat: no-repeat;
}

.ft-list .item {
  padding-top: 20px;
  color: #ccc;
  font-size: 14px;
  position: relative
}

.ft-list .item a {
  color: #ccc
}

.ft-list .item a:hover {
  color: #22bfa7
}

.ft-list .item.pop:before {
  content: '';
  position: absolute;
  width: 120px;
  height: 120px;
  background-color: #fff;
  background-repeat: no-repeat;
  background-position: center center;
  left: 190px;
  display: none
}

.ft-list .item.pop:hover:before {
  display: block
}

.ft-list .item.pop.app:before {
  top: 20px;
  background-image: url(../assets/download.png);
}

.ft-list .item.weixin:after {
  content: '';
  display: inline-block;
  vertical-align: middle;
  width: 18px;
  height: 18px;
  background-image: url(../assets/contact.png);
  background-position: -35px -466px;
  background-repeat: no-repeat
}

.ft-list .item.pop.weixin:before {
  bottom: -70px;
  background-image: url(../assets/contact.png);
}


</style>
