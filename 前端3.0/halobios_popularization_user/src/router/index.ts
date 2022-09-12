import {
    createRouter,
    createWebHashHistory
    } from 'vue-router'
    // 引入routes.ts文件
    import routes from '../router/router'
    const router = createRouter({
                history: createWebHashHistory(), //createWebHashHistory是hash模式
                routes
    });
    export default router