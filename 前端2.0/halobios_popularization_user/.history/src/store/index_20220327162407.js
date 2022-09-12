import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
		// 是否已登录 true false
			judge_login:false,
			user_id:"",
			// 用户选择查看的内容：收藏、点赞、评论
			user_type:"",
	},
	mutations:{
		judgeLogin(state,judge_login){
			state.judge_login=judge_login;
			
		},
		getUserId(state,user_id){
			state.user_id=user_id;
			console.log("用户id为："+user_id);
     
		},
		chioceUserType(state,user_type){
			
			state.user_type=user_type;
			window.alert( "存储用户选择类型为"+user_type);
		}

	},
	actions:{
		updataJudgeLogin(context,judge_login){
			context.commit('judgeLogin',judge_login)
		},
		updateUserId(context, user_id) {
            context.commit('getUserId',user_id)
        },
	},
	modules:{
		
	},
	
});

export default store;