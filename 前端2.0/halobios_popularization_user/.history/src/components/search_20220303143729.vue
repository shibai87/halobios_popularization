<template>
    <div class="index_search">   
        <van-row>
            <van-col span="2">
                <van-icon name="arrow-left"  size="30" @click="pageBack"  class="search"/>
            </van-col>
            <van-col span="22">
                <form action="/">
                    <van-search v-model="search_value" show-action placeholder="请输入搜索关键词" @search="onSearch"
                        @cancel="onCancel" />
                </form>
            </van-col>
        </van-row>
    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    export default {
         name: 'search',
        data() {
            return {   
                search_value: '',
            }
        },
     
        methods: {
            pageBack() {
                window.history.back()
            },
            onSearch(val) {
                Toast(val);
            },
            onCancel() {
                Toast('取消');
            },
        }
    }
</script>

<style>
.search{
    padding: 10px 12px
}
</style>