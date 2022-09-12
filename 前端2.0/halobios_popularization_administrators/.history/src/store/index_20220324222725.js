import Vue from 'vue'
import Vuex from 'vuex'



Vue.use(Vuex);

const store= new Vuex.Store({
	state:{
			step:"",
			sort_value:""
	},
	mutations:{
		// 分类
			getStep(state,step,sort_value){
				state.step=step;
				state.sort_value=sort_value
				console.log(step)
				console.log(sort_value)
			},
			getSortValue(state,sort_value){
				state.sort_value=sort_value
				console.log(sort_value)
			}
	},
	modules:{
		
	},
	
});

export default store;