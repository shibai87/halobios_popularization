import Vue from 'vue'
import App from './App.vue'

import router from './router/router'
import store from './store/index'
import axios from 'axios';
import VueAxios from 'vue-axios'
Vue.use(VueAxios,axios); 

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

Vue.prototype.$axios=axios;
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
