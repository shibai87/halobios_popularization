import { RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
    {
        path:"/",
        name:"Index",
        component:()=>import("../views/index.vue")
    },
    {
        path:'/user_center',
        name:"User_center",
        component:()=>import("../views/user/user_center.vue")
    },
    {
        path:"/login",
        name:"login",
        component:()=>import("../views/user/login.vue")
    },
    {
        path:"/register",
        name:"register",
        component:()=>import("../views/user/register.vue")
    },
    {
        path:"/user_infos",
        component:()=>import("../views/user/user_infos.vue")
    },
   
    // {
    //     path:"/user_notice",
    //     component:User_notice,
    // },

    {
        path:"/knowledge_details",
        component:()=>import("../views/knowledge/knowledge_details.vue")
    },
    {
        path:"/activity_details",
        component:()=>import("../views/activity/activity_detail.vue")
    },
    {
        path:"/classroom_details",
        component:()=>import("../views/class/classroom_detail.vue")
    },
    {
        path:"/user_comment",
        component:()=>import("../views/user/user_comment.vue")
    },
    {
        path:"/user_other_operation",
        component:()=>import("../views/user/user_other_operation.vue")
    },
    {
        path:"/user_activity",
        component:()=>import("../views/user/user_activity.vue")
    },

    {
        path:"/demo",
        name:"Demo",
        component:()=>import("../views/demo.vue")
    },
    ];
    export default routes