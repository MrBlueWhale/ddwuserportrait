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
<!--        <span>您好：{{user.name}}</span>-->
        <router-link to="/about"></router-link>
      </a-menu-item>

<!--      登录菜单-->
      <a-popconfirm
          title="确认退出登录?"
          ok-text="是"
          cancel-text="否"
          @confirm="logout()"
      >
        <a class="login-menu" v-show="user.uid">
          <span>退出登录</span>
        </a>
      </a-popconfirm>
      <a class="login-menu" v-show="user.uid">
        <span>您好：{{user.userName}}-{{user.name}}</span>
      </a>

      <a class="login-menu" v-show="!user.uid" @click="showLoginModal">
        <span>登录</span>
      </a>


    </a-menu>

    <a-modal
        title="登录"
        v-model:visible="loginModalVisible"
        :confirm-loading="loginModalLoading"
        @ok="login"
    >
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.userName" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>

  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import { createFromIconfontCN } from '@ant-design/icons-vue';

import axios from 'axios';
import { message } from 'ant-design-vue';
import store from "@/store";
import router from "@/router";

declare let hexMd5: any;
declare let KEY: any;

const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_2558111_009ekmettrll3.js',
});

export default defineComponent({
  name: 'admin-header',

  setup () {
    // 登录后保存
    const user = computed(() => store.state.user);
    // const user = ref({
    //   uid: 0,
    //   token: '0000',
    // });

    // 用来登录
    const loginUser = ref({
      userName: "admin4",
      password: "123qwe"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("开始登录");
      loginModalLoading.value = true;
      // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      loginUser.value.password = hexMd5(loginUser.value.password);
      axios.post('/user/login', loginUser.value).then((response) => {
        loginModalLoading.value = false;
        const data = response.data;
        if (data.success) {
          loginModalVisible.value = false;
          message.success("登录成功！");

          // user.value = data.content

          store.commit("setUser", data.content);
        } else {
          message.error(data.message);
        }
      });
    };

    // 退出登录
    const logout = () => {
      console.log("退出登录开始");
      axios.get('/user/logout/' + user.value.token).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("退出登录成功！");

          router.push({
            path: '/login',
            query: {
              // pid: data.content.pid
            }
          })

          //清除缓存
          store.commit("setUser", {});
        } else {
          message.error(data.message);
        }
      });
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login,
      user,
      logout
    }
  },



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

  .login-menu {
    float: right;
    color: white;
    padding-left: 10px;
  }

a.router-link-active{ font-weight: bold;}
a.router-link-active span::before{ content: ''; width: 100%; height: 1px; display: block; }

</style>
