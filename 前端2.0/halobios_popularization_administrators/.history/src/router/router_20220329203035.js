import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)


import Login from "../view/login.vue"
import Index from "../view/index.vue"

import EditKnowledge from "../view/knowledge/editknowledge_detail.vue"
import EditActivity from "../view/activity/editactivity_detail.vue"
import EditVideo from "../view/VIDREO/editvideo_detail.vue"

import Demo from "../components/dem.vue"

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
        path:'/editvideo_detail',
        component:EditVideo
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