import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			step:"",
			sort_id:[]
	},
	mutations:{
		getSortId(state,step,sort_id){
			state.sort_id=sort_id
			console.log(step,sort_id)
		},
		
	},
	modules:{
		
	},
	actions:{
	
	}
});

export default store;