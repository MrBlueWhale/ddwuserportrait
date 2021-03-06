import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import TempApp from '../views/TempApp.vue'
import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
// import About from '../views/About.vue'
import PortraitEchartsExample from '../views/portrait/portrait-echarts-example.vue'
import PortraitBaseTag from '../views/portrait/portrait-basetag.vue'
import PortraitGroupTag from '../views/portrait/portrait-grouptag.vue'
import PortraitUserPortrait from '../views/portrait/portrait-userportrait.vue'
import SystemAuthority from '../views/system/system-authority.vue'
import SystemLog from '../views/system/system-log.vue'
import SystemRole from '../views/system/system-role.vue'
import SystemUser from '../views/system/system-user.vue'
import {Tool} from "@/util/tool";
import store from "@/store";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'TempApp',
        // component: Home
        meta: {
            loginRequire: true,
        },
        component: TempApp,
        redirect: '/home',
        children: [{
            path: '/home',
            name: 'Home',
            component: Home
        }, {
            path: '/profile',
            name: 'Profile',
            component: Profile
        },
        ]
    },
    // {
    //     path: '/home',
    //     name: 'Home',
    //     component: Home
    // },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },

    {
        path: '/portrait/',
        name: 'Portrait',
        meta: {
            loginRequire: true,
        },
        component: TempApp,
        redirect: '/userportrait/basetag',
        children: [
            {
                path: 'basetag',
                name: 'PortraitBaseTag',
                component: PortraitBaseTag
            },
            {
                path: 'grouptag',
                name: 'PortraitGroupTag',
                component: PortraitGroupTag
            },
            {
                path: 'userportrait',
                name: 'PortraitUserPortrait',
                component: PortraitUserPortrait
            },{
                path: 'echarts',
                name: 'PortraitEchartsExample',
                component: PortraitEchartsExample
            },
        ],
    },
    {
        path: '/system/',
        name: 'System',
        component: TempApp,
        meta: {
            loginRequire: true,
        },
        redirect: '/system/user-manage',
        children: [
            {
                path: 'user-manage',
                name: 'SystemUser',
                component: SystemUser
            },
            {
                path: 'role-manage',
                name: 'SystemRole',
                component: SystemRole
            },
            {
                path: 'authority-manage',
                name: 'SystemAuthority',
                component: SystemAuthority
            },
            {
                path: 'log-manage',
                name: 'SystemLog',
                component: SystemLog
            },
        ],
    },

    {
        path: '/:catchAll(.*)',
        name: '/404',
        component: () => import('../views/404.vue')
    },

    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})


// ??????????????????---????????????
router.beforeEach((to, from, next) => {
    // ????????????meta.loginRequire?????????????????????
    if (to.matched.some(function (item) {
        console.log(item, "???????????????????????????", item.meta.loginRequire);
        return item.meta.loginRequire
    })) {
        const loginUser = store.state.user;
        if (Tool.isEmpty(loginUser)) {
            console.log("??????????????????");
            // ????????? ???????????? ?????? ?????????
            // next('/');
            next('/login');
        } else {
            next();
        }
    } else {
        // ?????????????????????
        next();
    }
});


export default router
