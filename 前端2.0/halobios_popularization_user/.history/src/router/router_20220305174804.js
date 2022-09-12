import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)

import Index from '../views/index.vue'
import User from "../views/user_center.vue"
import Search from "../components/search.vue"
import Login from "../components/login.vue"
import Register from "../components/register.vue"

import User_center_details from "../views/user_center_details.vue"
import Sort_details from "../views/sort_details.vue"
import Know_details from "../views/home_detail.vue"

import demo from "../components/demo1.vue"
const routes=[
    {
        path:'/',
        component:Index,
    },
  
    {
        path:'/search',
        component:Search,
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
        path:"/user_center_details",
        component:User_center_details,
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
    },
   
]


const router=new VueRouter({
    routes
});

export default router