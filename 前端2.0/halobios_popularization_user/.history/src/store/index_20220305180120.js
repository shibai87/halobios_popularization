import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
		// 是否已登录 true false
			judge_login:false,
			// 用户选择查看的内容：收藏、点赞、评论
			type:"",
	},
	mutations:{
		changeTitle(state,title){
			state.title=title;
		},
		judgeLogin(state,judge_login){
			state.judge_login=judge_login;
		},
		judgeNoHome(state,judge_no_home){
			state.judge_no_home=judge_no_home;
		},
		chioce(state,type){
			state.type=type;
		}

	},
	modules:{
		
	},
	
});

export default store;