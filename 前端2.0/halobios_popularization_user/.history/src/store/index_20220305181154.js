import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
		// 是否已登录 true false
			judge_login:false,
			// 用户选择查看的内容：收藏、点赞、评论
			user_type:"",
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
		chioceUserType(state,user_type){
			
			state.user_type=user_type;
			window.alert( user_type);
		}

	},
	modules:{
		
	},
	
});

export default store;