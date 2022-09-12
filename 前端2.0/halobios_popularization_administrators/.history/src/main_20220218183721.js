import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import axios from 'axios';

import ElementUI from 'element-ui';//1 eui组件
import 'element-ui/lib/theme-chalk/index.css';// 2 eui 样式

import Vant from 'vant';
import 'vant/lib/index.css';

import Index from './views/index.vue';//主页
import Login from'./views/login.vue';//登录
import register from'./views/register.vue';//注册
import search from'./components/search.vue';

import robShop from'./views/robShop.vue';
import FaceCare from'./views/FaceCare.vue';
import BeautySuit from'./views/BeautySuit.vue';

import Order from'./views/Order.vue';//列表页
import details from'./views/details.vue';//详情页
import details2 from'./views/details2.vue';//详情页
import purchase from'./views/purchase.vue';//立即购买
import myshop from'./views/myshop.vue';//我的购物车
import MyOrder from'./views/MyOrder.vue';//我的订单

import my from'./views/my.vue';//个人中心

import store from './store/index';

// 3 注册给vue
//第三方库需要use一下才能用
Vue.use(ElementUI);
Vue.use(Vant);

Vue.use(VueRouter);

//给Vue函数添加一个原型属性$axios 指向 Axious
Vue.prototype.$axios=axios;
export const Bus=new Vue() 
Vue.config.productionTip = false

//定义routes路由的集合，数组类型
let router=new VueRouter({
  mode:'history',
  routes:[
    {
      path:'/',
      name:'index',
      component:Index,
      meta:{
        login_required:false
      }
    },
    {
      path:'/login',
      name:'login',
      component:Login,
      meta:{
        login_required:false
      }
   },
   {
    path:'/register',
    name:'register',
    component:register,
    
 },
    {
      path:'/Order',
      name:'Order',
      component:Order,
      meta:{
        login_required:true
      }
    },
    {
      path:'/details/:id',
      name:'details',
      component:details,
    },
    {
      path:'/details2',
      name:'details2',
      component:details2,
    },
    {
      path:'/purchase',
      name:'purchase',
      component:purchase,
    },
    {
      path:'/myshop',
      name:'myshop',
      component:myshop,
    },
    {
      path:'/MyOrder',
      name:'MyOrder',
      component:MyOrder,
    },

    {
      path:'/robShop',
      name:'robShop',
      component:robShop,
      children:[
        {
          path:'/FaceCare',
          component:FaceCare,
          name: 'FaceCare'
        },
        {
          path:'/BeautySuit',
          component:BeautySuit,
          name: 'BeautySuit'
        },
      ]
    },
    {
      path:'/search',
      component:search,
      name: 'search'
    },
    {
      path:'/my',
      component:my,
      name: 'my',
      meta:{
        login_required:true
      }
    },
 
     

  ]
});

// 路由拦截
router.beforeEach(function(to, from, next) {
	var logged_in = false
	var logged= window.localStorage.getItem('flag')
	if(logged=='true'){
		logged_in=true
	}
	if (!logged_in && to.matched.some(function(item) {
			return item.meta.login_required
		})) {
      alert('你还没登录，请先登录');
		next('/login');
	} else {
		next();
    
	}
});

// 脚手架手动挂载
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')