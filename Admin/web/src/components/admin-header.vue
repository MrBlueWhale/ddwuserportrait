<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys1"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/admin-home">
        <icon-font class="icons-bar" type="icon-huanying" style="font-size:24px" />
        <span>首页</span>
        <router-link to="/admin/home"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin-participant">
        <icon-font class="icons-bar" type="icon-cansaizhe" style="font-size:24px"/>
        <span>参赛者管理</span>
        <router-link to="/admin/participant"></router-link>
<!--        <router-link to="/admin/participant">参赛者管理</router-link>-->
      </a-menu-item>
      <a-menu-item key="/admin-sponsor">
        <icon-font class="icons-bar" type="icon-huodongzhubanfang01" style="font-size:24px"/>
        <span>主办方管理</span>
        <router-link to="/admin/sponsor"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin-contest">
        <icon-font class="icons-bar" type="icon-jingsai" style="font-size:24px"/>
        <span>竞赛管理</span>
        <router-link to="/admin/contest"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin-team">
        <icon-font class="icons-bar" type="icon-duiwu" style="font-size:24px"/>
        <span>组队管理</span>
        <router-link to="/admin/team"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin-notification">
        <icon-font class="icons-bar" type="icon-tongzhi" style="font-size:24px"/>
<!--        <icon-font class="icons-bar" type="icon-duiwu" />        -->
        <span>通知管理</span>
        <router-link to="/admin/notification"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin-discussion">
        <icon-font class="icons-bar" type="icon-shequn" style="font-size:24px"/>
        <span>社群管理</span>
        <router-link to="/admin/discussion"></router-link>
      </a-menu-item>
      <a-menu-item key="/admin/about">
        <icon-font class="icons-bar" type="icon-guanyu" style="font-size:24px"/>
        <span>关于我们</span>
        <router-link to="/admin/about"></router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { createFromIconfontCN } from '@ant-design/icons-vue';

const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_2558111_009ekmettrll3.js',
});

export default defineComponent({
  name: 'admin-header',
  props: {
    msg: String,
  },
  components: {
    IconFont,
  },
});
</script>

<!--<style>-->

<!--a.router-link-active{ font-weight: bold;}-->
<!--a.router-link-active span::before{ content: ''; width: 100%; height: 1px; display: block; }-->

<!--</style>-->

<style scoped>
  .icons-bar{
  color: #A6ADB4;
}

a.router-link-active{ font-weight: bold;}
a.router-link-active span::before{ content: ''; width: 100%; height: 1px; display: block; }

</style>
