import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
		// 分类id列表
			sort_id_list:[],
			sort_id_list_length:0,
			// 科普知识的分类id
			sort_id_list_last:"",
	},
	mutations:{
		// 分类
			getSortIdList(state,sort_id_list){
				state.sort_id_list=sort_id_list;
				console.log("vuex:"+ sort_id_list)

			},
			getSortIdListLength(state,sort_id_list_length){
				state.sort_id_list_length=sort_id_list_length;
				console.log("vuex:"+sort_id_list_length)
			},
			getSortIdListLast(state,sort_id_list_last){
				state.sort_id_list_last=sort_id_list_last;
				console.log("vuex:"+typeof sort_id_list_last)
			},
		
	},
	modules:{
		
	},
	
});

export default store;