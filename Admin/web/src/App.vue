<template>
  <a-layout>
    <admin-header></admin-header>
    <router-view></router-view>
    <admin-footer></admin-footer>

  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, reactive, toRef, toRaw, UnwrapRef} from 'vue'

import {createFromIconfontCN, MailOutlined, LeftCircleOutlined, RightCircleOutlined} from '@ant-design/icons-vue';

import axios from 'axios';

import AdminHeader from '@/components/admin-header.vue';
import AdminFooter from '@/components/admin-footer.vue';

export default defineComponent({
  name: 'app',
  components: {
    AdminHeader,
    AdminFooter,

    MailOutlined,

  },

});
</script>


<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>