import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import TempApp from '../views/TempApp.vue'
import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
// import About from '../views/About.vue'
import PortraitBaseTag from '../views/portrait/portrait-basetag.vue'
import PortraitGroupTag from '../views/portrait/portrait-grouptag.vue'
import PortraitUserPortrait from '../views/portrait/portrait-userportrait.vue'
import SystemAuthority from '../views/system/system-authority.vue'
import SystemLog from '../views/system/system-log.vue'
import SystemRole from '../views/system/system-role.vue'
import SystemUser from '../views/system/system-user.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'TempApp',
    // component: Home
      component: TempApp,
      redirect: '/home',
      children: [{
          path: '/home',
          name: 'Home',
          component: Home
      },{
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
        },
    ],
  },
    {
        path: '/system/',
        name: 'System',
        component: TempApp,
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

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
