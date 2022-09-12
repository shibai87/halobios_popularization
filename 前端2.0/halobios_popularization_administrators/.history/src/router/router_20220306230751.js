import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)


import Login from "../view/login.vue"
import Index from "../view/index.vue"

import Knowledge from "../view/knowledge.vue"
import Protect from "../view/protect.vue"
import Class from "../view/class.vue"
import UserManage from "../view/user_management.vue"

const routes=[
    {
        path:'/',
        component:Login,
    },
    {
        path:'/index',
        component:Index,
    },

    {
        path:'/knowledge',
        component:Knowledge,
    },
    {
        path:'/protect',
        component:Protect,
    },
    {
        path:'/class',
        component:Class,
    },
    {
        path:'/user_manage',
        component:UserManage,
    },
  
  
   
]


const router=new VueRouter({
    routes
});

export default router