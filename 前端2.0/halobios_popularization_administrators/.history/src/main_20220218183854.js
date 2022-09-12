import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';



import Login from'./views/login.vue';//登录




//定义routes路由的集合，数组类型
let router=new VueRouter({
  mode:'history',
  routes:[
    {
      path:'/',
      name:'login',
      component:Login,
    },
 
     

  ]
});



// 脚手架手动挂载
new Vue({
  router,
  
  render: h => h(App),
}).$mount('#app')