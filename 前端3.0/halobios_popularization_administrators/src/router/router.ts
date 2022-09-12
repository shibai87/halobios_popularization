import { RouteRecordRaw } from "vue-router";
const routes: Array<RouteRecordRaw> = [
    {
        path:"/",
        name:"Login",
        component:()=>import("../views/login.vue")
    },
    {
        path:"/index",
        name:"index",
        component:()=>import("../views/index.vue")
    },
    {
        path:'/commentList',
        component:()=>import("../components/commentList.vue")
    },
    {
        path:'/report',
        component:()=>import("../views/user/report.vue")
    },
    {
        path:'/editknowledge_detail',
        component:()=>import("../views/knowledge/editknowledge_detail.vue")
    },
    {
        path:'/editactivity_detail',
        component:()=>import("../views/activity/editactivity_detail.vue")
    },
    {
        path:'/editclass_detail',
        component:()=>import("../views/class/editclass_detail.vue")
    },
    {
        path:'/report',
        component:()=>import("../views/user/report.vue")
    },
    {
        path:'/reportlist',
        component:()=>import("../views/user/reportList.vue")
    }
    ];
    export default routes