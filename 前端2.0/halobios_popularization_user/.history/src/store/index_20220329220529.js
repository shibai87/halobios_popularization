import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
		// 是否已登录 true false
			judge_login:false,
			user_id:0,
				// 科普知识选择的分类id
				sort_id_list_last:"",
				// 用户选择查看的知识id
				knowledge_id:"",
				activity_id:"",
				classroom_id:"",
			// 用户选择查看的内容：收藏、点赞、评论
			user_type:"",
	},
	mutations:{
		// 
		judgeLogin(state,judge_login){
			state.judge_login=judge_login;
			console.log("judge_login为："+judge_login);
			
		},
		getUserId(state,user_id){
			state.user_id=user_id;
			console.log("用户id为："+user_id);
     
		},
		// 
		getSortIdListLast(state,sort_id_list_last){
			state.sort_id_list_last=sort_id_list_last;
			console.log("vuex:"+sort_id_list_last)
		},
			// 存储需编辑的id
			getKnowledge(state,knowledge_id){
				state.knowledge_id=knowledge_id
				console.log("vuex:"+knowledge_id)
			},
				// 存储需编辑的id
				getActivity(state,activity_id){
					state.activity_id=activity_id
					console.log("vuex:"+activity_id)
				},
				// 存储需编辑的id
				getclassroom(state,classroom_id){
					state.classroom_id=classroom_id
					console.log("vuex:"+classroom_id)
				},
		// 
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