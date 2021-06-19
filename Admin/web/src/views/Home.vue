<template>

    <a-layout>
        <a-layout style="padding: 0 24px 24px">

            <a-layout-content
                    :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
            >
                <div class="intro-box" style="padding-left: 41%;
  padding-bottom: 30px;">
                    <div class="intro" >
                        <span>欢迎使用用户画像！！！</span>
                    </div>
                </div>

                <div style="background-color: #eaedf2; padding: 20px">

                    <a-row :gutter="10">
                        <a-col :span="6">
                            <a-card hoverable bordered style="width: 1400px; height: 1200px">
                                <template #cover>
                                    <img
                                            alt="example"
                                            src="/img/welcome_image.jpg"
                                            style="height: 600px;"
                                    />
                                </template>

                            </a-card>

                        </a-col>
                    </a-row>

                    <div class="divide-box" style="padding: 30px;"></div>

                </div>


            </a-layout-content>
        </a-layout>
    </a-layout>

</template>

<script lang="ts">
    import {defineComponent, onMounted, ref, reactive, toRef} from 'vue';

    import { SmileTwoTone, HeartTwoTone, CheckCircleTwoTone } from '@ant-design/icons-vue';

// import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
// import AdminHeader from '@/components/admin-header.vue'; // @ is an alias to /src
// import AdminFooter from '@/components/admin-footer.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Home',


    //放一些参数定义，方法定义
    setup() {
        console.log("setup");
        // 使用ref()定义响应式数据
        const demos = ref();
        //reactive中放入对象 并自定义属性
        const demos2 = reactive({demos: []});

        //初始化逻辑都写到onMounted()里
        onMounted(() => {
            console.log("onMounted");

        });

        //html代码要拿到响应式变量 需要在setup的最后return
        return {
            demos,
            demos_reactive: toRef(demos2, "demos"),
            pagination : {
                onChange: (page: any) => {
                    console.log(page);
                },
                pageSize: 3,
            },
            actions: [
                { type: 'StarOutlined', text: '156' },
                { type: 'LikeOutlined', text: '156' },
                { type: 'MessageOutlined', text: '2' },
            ],
        }
    },


    components: {

        SmileTwoTone,
        HeartTwoTone,
        CheckCircleTwoTone,
    },

});
</script>