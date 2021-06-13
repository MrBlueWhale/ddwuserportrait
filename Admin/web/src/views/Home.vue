<template>
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
        <a-sub-menu key="portrait">
          <template #title>
              <span>
                <user-outlined/>
                画像管理
              </span>
          </template>
          <a-menu-item key="userportrait">用户画像</a-menu-item>
          <a-menu-item key="basetag">基础标签</a-menu-item>
          <a-menu-item key="grouptag">组合标签</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="system">
          <template #title>
              <span>
                <laptop-outlined/>
                系统设置
              </span>
          </template>
          <a-menu-item key="user">用户管理</a-menu-item>
          <a-menu-item key="role">角色管理</a-menu-item>
          <a-menu-item key="authority">权限管理</a-menu-item>
          <a-menu-item key="log">日志管理</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="engine">
          <template #title>
              <span>
                <notification-outlined/>
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
        Content
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
// import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
// import AdminHeader from '@/components/admin-header.vue'; // @ is an alias to /src
// import AdminFooter from '@/components/admin-footer.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Home',
  components: {
    // HelloWorld,
    // AdminHeader,
    // AdminFooter,
  },
});
</script>
