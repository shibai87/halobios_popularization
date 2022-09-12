import {createStore} from "vuex"
export default createStore({
	state:{
		// 分类知识
			// 
			judge_new_sort:false,
			// 选择的分类id
			sort_id_last:"",
			judge_options6:true,
			// 用户选择查看的内容：收藏、点赞、评论
			user_type:"",
			activity_id:"",
			classroom_id:""
	},
	mutations:{
	// 知识
		// 分类
		judgeNewSort(state,judge_new_sort){
			state.judge_new_sort=judge_new_sort;
			// console.log("vuex:"+judge_new_sort)
		},
		getSortIdLast(state,sort_id_last){
			state.sort_id_last=sort_id_last;
			console.log("vuex:"+sort_id_last)
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
			console.log("vuex:"+user_type)
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

})