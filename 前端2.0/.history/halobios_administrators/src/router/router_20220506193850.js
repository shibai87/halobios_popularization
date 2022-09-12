import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)


import Login from "../view/login.vue"
import Index from "../view/index.vue"

import EditKnowledge from "../view/knowledge/editknowledge_detail.vue"
import EditActivity from "../view/activity/editactivity_detail.vue"
import EditClass from "../view/class/editclass_detail.vue"

import CommentList from "../view/commentList.vue"
import ReportList from "../view/user/reportList.vue"
import Report from "../view/user/report.vue"

import Demo from "../test/dem.vue"

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
        path:'/editknowledge_detail',
        component:EditKnowledge
    },
    {
        path:'/editactivity_detail',
        component:EditActivity
    },
    {
        path:'/editclass_detail',
        component:EditClass
    },
    {
        path:'/commentList',
        component:CommentList
    },
    {
        path:'/report',
        component:Report
    },
    {
        path:'/reportlist',
        component:ReportList
    },
    
    {
        path:'/demo',
        component:Demo,
    },

  
   
]


const router=new VueRouter({
    routes
});

export default router