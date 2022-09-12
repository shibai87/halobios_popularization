<template>
		<div class="slide-show" @mouseover="clearInv()" @mouseout="runInv()" >
			<div class="slide-img"  speed='1000'>
				<a>
					<img :src="slides[nowIndex].src">
				</a>
			</div>
		</div>
</template>
<script>
export default {
	name: 'App',
	props:['speed'],
	components: {
		// HelloWorld
	},
	data() {
		return{
				slides: [
				{
					src: require('../assets/images/1.jpeg'),
					href: 'detail/analysis'
				},
				{
					src: require('../assets/images/1.jpeg'),
					href: 'detail/analysis'
				},
			],
			nowIndex:0
		}
	},
	computed:{
		nextpage(){
			if(this.nowIndex===this.slides.length-1){
				return 0
			}else{
					return this.nowIndex+1
			}
		},
		prevpage(){
			if(this.nowIndex===0){
				return this.slides.length-1
			}else{
				return this.nowIndex-1
			}
	
		}
	},
	methods:{
		goto(index){
			this.nowIndex=index
		},
		runInv(){
			this.invId=setInterval(()=>{
				this.goto(this.nextpage)
			},this.speed)
		},
		clearInv(){
			clearInterval(this.invId)
		}
	},
	mounted(){
		this.runInv()
	}
};
</script>
<style scoped="scoped">
	h2 {
		padding: 0;
		margin: 0;
	}
	.slide-show {
		position: relative;
		margin: 15px 15px 15px 0;
		width: 100%px;
		height: 500px;
		overflow: hidden;
	}	
	.slide-show h2 {
		position: absolute;
		width: 100%;
		height: 100%;
		color: #fff;
		background: #000;
		opacity: .5;
		bottom: 0px;
		height: 30px;
		text-align: left;
		padding-left: 15px;
	}
	
	.slide-img {
		width: 100%;
	}
	
	.slide-img img {
		width: 100%;
		position: absolute;
		top: 0;
		left: 0;
	}
	.slide-pages {
		position: absolute;
		bottom: -10px;
		right: 15px;
		font-size: 20px;
		font-weight: bold;
	}
	
	.slide-pages li {
		display: inline-block;
		padding: 0 10px;
		cursor: pointer;
		color: #fff;
	}
	
	.slide-pages li .on {
		text-decoration: underline;
		color: red;
	}
	img{
		width:100%;
		height: 500px;
	}
</style>
