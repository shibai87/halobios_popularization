import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			step:""
	},
	mutations:{
			getStep(state,step){
				state.step=step;
				console.log(step)
			}
	},
	modules:{
		
	},
	
});

export default store;