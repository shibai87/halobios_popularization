<template>
    <div id="index_search">   
        <!-- index_search_head -->
        <van-nav-bar title="登录" left-arrow fixed @click-left="pageBack" />
        <!--  -->
        <van-row>
            <van-col span="2">
                <van-icon name="arrow-left"  size="35" @click="pageBack"  class="search"/>
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
         name: 'index_search',
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
 #index_search .van-nav-bar__content {
    height: 70px;
  }

  #index_search .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #index_search .van-icon:before {
    color: #000;
    font-size: 35px;

  }

</style>