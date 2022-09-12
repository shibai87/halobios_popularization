import Vue from 'vue'
import App from './App.vue'

import router from './router/router'
import store from './store/index'
import axios from 'axios';
import VueAxios from 'vue-axios'
Vue.use(VueAxios,axios); 

 import 'amfe-flexible'

import less from 'less'
Vue.use(less)
import 'vant/lib/index.less';

import { Col, Row } from 'vant';
import { Field } from 'vant';
import { Search } from 'vant';
import { Icon } from 'vant';
import { Tab, Tabs } from 'vant';
import { NavBar } from 'vant';
import { Swipe, SwipeItem } from 'vant';
import { Lazyload } from 'vant';
import { Form } from 'vant';
import { Button } from 'vant';
import { PasswordInput, NumberKeyboard } from 'vant';
import { Badge } from 'vant';
import { Image as VanImage } from 'vant';
import { List } from 'vant';
import { Dialog } from 'vant';
import { PullRefresh } from 'vant';
import { Uploader } from 'vant';
import { Popup } from 'vant';
import {cell} from "vant";  
import { Checkbox, CheckboxGroup } from 'vant';
import { RadioGroup, Radio } from 'vant';
import { DropdownMenu, DropdownItem } from 'vant';

Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(Checkbox);
Vue.use(CheckboxGroup);
Vue.use(cell);
Vue.use(Popup);
Vue.use(Uploader);
Vue.use(PullRefresh);
// 全局注册
Vue.use(Dialog);
Vue.use(List);
Vue.use(VanImage);
Vue.use(Badge);
Vue.use(PasswordInput);
Vue.use(NumberKeyboard);
Vue.use(Button);
Vue.use(Form);
Vue.use(Lazyload);
Vue.use(Col);
Vue.use(Row);
Vue.use(Field);
Vue.use(Search);
Vue.use(Icon);
Vue.use(Tab);
Vue.use(Tabs);
Vue.use(NavBar);
Vue.use(Swipe);
Vue.use(SwipeItem);


import VideoPlayer from 'vue-video-player'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'
Vue.use(VideoPlayer)

Vue.prototype.$axios=axios;
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
