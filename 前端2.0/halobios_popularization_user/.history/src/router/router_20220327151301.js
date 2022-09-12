import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)

import Index from '../views/index.vue'
import User from "../views/user/user_center.vue"

import Login from "../views/user/login.vue"
import Register from "../views/user/register.vue"

import User_infos from "../views/user_infos.vue"
import User_center_details from "../views/user_center_details.vue"
import User_notice from "../views/user_notice.vue"
import Sort_details from "../views/sort_details.vue"
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
        path:"/user_center_details",
        component:User_center_details,
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
        path:"/sort_details",
        component:Sort_details,
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