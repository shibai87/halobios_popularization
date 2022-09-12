import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			sort_id_list:[],
			sort_id_list_last:[]
	},
	mutations:{
		// 分类
			getSortIdList(state,sort_id_list){
				state.sort_id_list=sort_id_list;
				console.log("vuex"+sort_id_list)
			},
			getSortIdListLast(state,sort_id_list_last){
				state.sort_id_list_last=sort_id_list_last;
				console.log("vuex"+sort_id_list_last)
			},
		
	},
	modules:{
		
	},
	
});

export default store;