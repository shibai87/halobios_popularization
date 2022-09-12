import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			
			sort_id:[]
	},
	mutations:{
		getSortId(state,sort_id){
			state.sort_id=sort_id
			console.log(sort_id)
		}
	},
	modules:{
		
	},
	
});

export default store;