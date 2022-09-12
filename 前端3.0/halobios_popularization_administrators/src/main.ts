import { createApp } from 'vue'
import App from './App.vue'
//路由配置
import router from './router'
//状态管理
import store from '../src/store/index'
//完整引入UI库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)


// import './style.css'

//路由配置
app.use(router)
//状态管理
app.use(store)
//完整引入UI库
app.use(ElementPlus)

app.mount('#app')
