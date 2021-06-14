<template>
  <a-form
    ref="loginForm"
    :model="loginUser"
    :rules="rules"
    label-width="100px"
    class="loginForm sign-in-form"
  >
    <a-form-item label="用户名" >
      <a-input
        v-model="loginUser.userName"
        placeholder="请输入用户名"
      ></a-input>
    </a-form-item>
    <a-form-item label="密码" >
      <a-input
        v-model="loginUser.password"
        type="password"
        placeholder="请输入密码"
      ></a-input>
    </a-form-item>

    <a-form-item>
      <a-button
        @click="handleLogin('loginForm')"
        type="primary"
        class="submit-btn"
        >提交</a-button
      >
    </a-form-item>

    <!-- 找回密码 -->
    <div class="tiparea">
      <p>忘记密码？ <a>立即找回</a></p>
    </div>
  </a-form>
</template>

<script lang="ts">
import {ref, getCurrentInstance, computed} from "vue";
import store from "@/store";
import axios from "axios";
import {message} from "ant-design-vue";
import router from "@/router"

declare let hexMd5: any;
declare let KEY: any;

export default {
  // props: {
  //   // loginUser: {
  //   //   type: Object,
  //   //   required: true,
  //   // },
  //   rules: {
  //     type: Object,
  //     required: true,
  //   },
  // },

  name: 'LoginForm',

  setup() {
    // @ts-ignore
    const { ctx } = getCurrentInstance();

    const loginUser = ref({
      userName: "admin6",
      password: "123qwe"
    });

    // 登录后保存
    const user = computed(() => store.state.user);

    // 触发登录方法
    const handleLogin = (formName: string) => {

      console.log(formName)
      // alert("handleLogin!");

      console.log("开始登录");
      // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
      loginUser.value.password = hexMd5(loginUser.value.password);
      axios.post('/user/login', loginUser.value).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("登录成功！");

          router.push({
            path: '/home',
            query: {
              // pid: data.content.pid
            }
          })

          store.commit("setUser", data.content);
        } else {
          message.error(data.message);
        }
      });


      // ctx.$refs[formName].validate((valid: boolean) => {
      //   if (valid) {
      //     alert("submit!");
      //   } else {
      //     console.log("error submit!!");
      //     return false;
      //   }

      };

    return {
      handleLogin,
      loginUser,
      user,
    }

}

};

</script>
<style scoped>
/* form */
.loginForm {
  margin-top: 20px;
  background-color: #fff;
  padding: 20px 40px 20px 20px;
  border-radius: 5px;
  box-shadow: 0px 5px 10px #cccc;
}

.submit-btn {
  width: 100%;
}

.tiparea {
  text-align: right;
  font-size: 12px;
  color: #333;
}
.tiparea p a {
  color: #409eff;
}
</style>