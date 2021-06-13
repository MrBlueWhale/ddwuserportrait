<template>
  <a-layout>
    <admin-header></admin-header>
    <router-view></router-view>
    <!--    <a-layout-footer style="text-align: center">-->
    <!--      Dalao Template @2021-->
    <!--    </a-layout-footer>-->
    <admin-footer></admin-footer>

  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import AdminHeader from '@/components/admin-header.vue';
import AdminFooter from '@/components/admin-footer.vue';

export default defineComponent({
  name: 'app',
  components: {
    AdminHeader,
    AdminFooter,
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