import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			judge_login:false,
			//  true false
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
		}
	},
	modules:{
		
	},
	
});

export default store;