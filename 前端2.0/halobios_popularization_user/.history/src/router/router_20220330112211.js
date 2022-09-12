import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)

import Index from '../views/index.vue'


import Login from "../views/user/login.vue"
import Register from "../views/user/register.vue"
import User from "../views/user/user_center.vue"
import User_infos from "../views/user/user_infos.vue"

import User_notice from "../views/user_notice.vue"
import Knowledge_details from "../views/knowledge/knowledge_details.vue"
import Activity_detail from "../views/activity/activity_detail.vue"
import Class_detail from "../views/class/classroom_detail.vue"

import UserComment from "../views/user/user_comment.vue"
import UserOtherOperation from "../views/user/user_other_operation.vue"

import Know_details from "../views/home_detail.vue"
import demo from "../components/demo1.vue"
const routes=[
    {
        path:'/',
        component:Index,
    },
    {
        path:'/user',
        component:User,
    },
    {
        path:"/login",
        component:Login,
    },
    {
        path:"/register",
        component:Register,
    },
    {
        path:"/user_infos",
        component:User_infos,
    },
   
    {
        path:"/user_notice",
        component:User_notice,
    },

    {
        path:"/home_details",
        component:Know_details,
    },
    {
        path:"/knowledge_details",
        component:Knowledge_details,
    },
    {
        path:"/activity_details",
        component:Activity_detail,
    },
    {
        path:"/classroom_details",
        component:Class_detail,
    },
    {
        path:"/user_comment",
        component:UserComment,
    },
    {
        path:"/user_other_operation",
        component:UserOtherOperation,
    },

    {
        path:"/demo",
        component:demo,
        name:demo
    },
   
]


const router=new VueRouter({
    routes
});

export default router