<template>
<!--    <router-view></router-view>-->

  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-menu-item key="welcome">
          <MailOutlined />
          <span>欢迎</span>
        </a-menu-item>
        <a-menu-item key="profile">
          <UserOutlined />
          <span><router-link to="/profile">个人资料</router-link></span>
        </a-menu-item>
        <a-sub-menu key="portrait">
          <template #title>
              <span>
          <icon-font class="icons-bar" type="icon-yonghuhuaxiang" style="font-size:15px"/>
                画像管理
              </span>
          </template>
          <a-menu-item key="userportrait">
            <icon-font class="icons-bar" type="icon-cansaitubiaozhuanqu-" style="font-size:15px"/>
            <span>用户画像</span>
            <router-link to="/portrait/userportrait"></router-link>
          </a-menu-item>
          <a-menu-item key="basetag">
            <icon-font class="icons-bar" type="icon-biaoqian" style="font-size:15px"/>
            <span>基础标签</span>
            <router-link to="/portrait/basetag"></router-link>
          </a-menu-item>
          <a-menu-item key="grouptag">
            <icon-font class="icons-bar" type="icon-biaoqian2" style="font-size:15px"/>
            <span>组合标签</span>
            <router-link to="/portrait/grouptag"></router-link>
          </a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="system">
          <template #title>
              <span>
                <laptop-outlined/>
                系统设置
              </span>
          </template>
          <a-menu-item key="user">
            <icon-font class="icons-bar" type="icon-shouyetubiao2-23" style="font-size:15px"/>
            <span>用户管理</span>
            <router-link to="/system/user-manage"></router-link></a-menu-item>
          <a-menu-item key="role">
            <icon-font class="icons-bar" type="icon-jiaoseguanli" style="font-size:15px"/>
            <span>角色管理</span>
            <router-link to="/system/role-manage"></router-link></a-menu-item>
          <a-menu-item key="authority">
            <icon-font class="icons-bar" type="icon-quanxianguanli" style="font-size:15px"/>
            <span>权限管理</span>
            <router-link to="/system/authority-manage"></router-link></a-menu-item>
          <a-menu-item key="log">
            <icon-font class="icons-bar" type="icon-drxx05" style="font-size:15px"/>
            <span>日志管理</span>
            <router-link to="/system/log-manage"></router-link></a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="engine">
          <template #title>
              <span>
          <icon-font class="icons-bar" type="icon-hammer" style="font-size:15px"/>
                引擎管理
              </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <router-view></router-view>
      </a-layout-content>
    </a-layout>

  </a-layout>


</template>

<script lang="ts">
import {defineComponent} from 'vue';
import {createFromIconfontCN} from "@ant-design/icons-vue/es/index";

const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_2609195_kuaa21fd5m.js',
});

export default defineComponent({
  name: 'TempApp',
  components: {
    IconFont,
  },

});
</script>


<style>

</style>