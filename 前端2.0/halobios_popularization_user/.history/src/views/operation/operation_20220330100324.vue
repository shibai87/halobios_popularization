<template>
    <div>
        <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <van-dropdown-item v-model="value1" :options="option1" />
            <van-dropdown-item v-model="value2" :options="option2" />
        </van-pull-refresh>

    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    export default {
        name: '',
        inject: ["reload"],
        data() {
            return {
                value1: 0,
                value2: 'a',
                option1: [{
                        text: '全部商品',
                        value: 0
                    },
                    {
                        text: '新款商品',
                        value: 1
                    },
                    {
                        text: '活动商品',
                        value: 2
                    },
                ],
                option2: [{
                        text: '默认排序',
                        value: 'a'
                    },
                    {
                        text: '好评排序',
                        value: 'b'
                    },
                    {
                        text: '销量排序',
                        value: 'c'
                    },
                ],
            }
        },
        methods: {
            pageBack() {
                this.$router.push("/user")
            },
            // 刷新
            onRefresh() {
                setTimeout(() => {
                    Toast('刷新成功');
                    this.isLoading = false;
                    this.reload()
                }, 1000);
            },
        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>