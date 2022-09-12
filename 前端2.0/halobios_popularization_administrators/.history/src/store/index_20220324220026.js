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
	actions:{
		resetVuex(state){ undefined
			store.commit(state.sort_id, null)
			}
	}
});

export default store;