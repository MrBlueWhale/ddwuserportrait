import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from 'axios';
import {Tool} from "@/util/tool";
axios.defaults.baseURL = process.env.VUE_APP_SERVER;

// const app = createApp();
// app.config.productionTip = false;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    const token = store.state.user.token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {
    console.log('返回错误：', error);
    return Promise.reject(error);
});

axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    // const response = error.response;
    // const status = response.status;
    // if (status === 401) {
    //     // 判断状态码是401 跳转到首页或登录页
    //     console.log("未登录，跳到首页");
    //     store.commit("setUser", {});
    //     message.error("未登录或登录超时");
    //     router.push('/');
    // }
    //Promise.reject()如果出现了error 就不会执行后面的代码
    return Promise.reject(error);
});




// const app = createApp();
// app.config.productionTip = false;

const app = createApp(App)
app.use(store).use(router).use(Antd).mount('#app');

//全局使用图标
const icons: any = Icons;
for(const i in icons) {
    app.component(i, icons[i]);
}


//启动日志
console.log('环境', process.env.NODE_ENV);
console.log('服务端', process.env.VUE_APP_SERVER);
