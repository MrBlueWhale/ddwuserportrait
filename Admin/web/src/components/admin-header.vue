<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        theme="dark"
        mode="horizontal"
        v-model:selectedKeys="selectedKeys1"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/home">
        <icon-font class="icons-bar" type="icon-huanying" style="font-size:24px" />
        <span>首页</span>
        <router-link to="/home"></router-link>
      </a-menu-item>

      <a-menu-item key="/admin-notification">
        <icon-font class="icons-bar" type="icon-tongzhi" style="font-size:24px"/>
<!--        <icon-font class="icons-bar" type="icon-duiwu" />        -->
        <span>通知</span>
        <router-link to="/admin/notification"></router-link>
      </a-menu-item>

      <a-menu-item key="/about">
        <icon-font class="icons-bar" type="icon-guanyu" style="font-size:24px"/>
        <span>关于我们</span>
        <router-link to="/about"></router-link>
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
