import { createApp } from 'vue'
import App from './App.vue'
//路由配置
import router from './router'
//状态管理
import store from '../src/store/index'
// 手动引入 Dialog 组件的样式
import 'vant/es/dialog/style';
import 'vant/es/toast/style';
const app = createApp(App)

//路由配置
app.use(router)
//状态管理
app.use(store)
app.mount('#app')
