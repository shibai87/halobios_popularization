<template>
  <div>
    <van-notice-bar color="#1989fa" background="#ecf9ff" left-icon="info-o" speed=50 text="请按从左至右的顺序选择各级分类，并且请通过点击弹出框的确定按钮进行每一级分类的选择！"/>
       <van-row gutter="5">
     
  <van-col span="6"> <van-button type="primary" hairline plain @click="showPopup(-1,-1)">第零级 </van-button></van-col>
  <van-col span="6"><van-button type="primary" hairline plain  @click="showPopup(0,options0_value)">第一级 </van-button></van-col>
  <van-col span="6"> <van-button type="primary" hairline plain  @click="showPopup(1,options1_value)">第二级 </van-button></van-col>
    <van-col span="6"><van-button type="primary" hairline plain @click="showPopup(2,options2_value)">第三级 </van-button></van-col>
  <van-col span="6"><van-button type="primary" hairline plain  @click="showPopup(3,options3_value)">第四级 </van-button></van-col>
  <van-col span="6"> <van-button type="primary" hairline plain  @click="showPopup(4,options4_value)">第五级 </van-button></van-col>
  <van-col span="6"> <van-button type="primary" hairline plain  @click="showPopup(5,options5_value)">第六级 </van-button></van-col>
</van-row>
   
    
    
       
     
    
      
     <!-- <van-button type="primary" hairline plain  @click="showPopup(6,options6_value)">第七级 </van-button> -->
    <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
      <van-picker title="请选择第零级分类" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel"
        @change="onChange"/>
    </van-popup>
    
    <van-row gutter="5">
      <div>您选择的分类为：</div>
  <van-col span="6">{{options0_value}}</van-col>
  <van-col span="6">{{options1_value}}</van-col>
  <van-col span="6">{{options2_value}}</van-col>
    <van-col span="6">{{options3_value}}</van-col>
  <van-col span="6">{{options4_value}}</van-col>
  <van-col span="6">{{options5_value}}</van-col>
  <van-col span="6">{{options6_value}}</van-col>
</van-row>
    <!-- <div >---{{options1_value}}---{{options2_value}}---{{this.options3_value}}---{{this.options4_value}}---{{this.options5_value}}---{{this.options6_value}}</div> -->
  </div>
</template>

<script>
  import {
    Toast
  } from 'vant';

  export default {
    name: '',
    data() {
      return {
        show: false,
          columns:[],
        // columns: ['杭州', '宁波', '温州', '绍兴', '湖州', '嘉兴', '金华', '衢州'],

        // 分类
        // 第几级
        step:"",
        // 存储所有分类
        sorts: [],
        // 分级存储各级分类
        options0: [],
        options0_value: '',
        options1: [],
        options1_value: '',
        options2: [],
        options2_value: '',
        options3: [],
        options3_value: '',
        options4: [],
        options4_value: '',
        options5: [],
        options5_value: '',
        options6: [],
        options6_value: '',
      }
    },
    methods: {
      showPopup(step,value) {
         console.log(step)
      this.step=step;
        if(step==-1){
              this.columns=this.options0
              this.getSort(value, step)
        }else{
         this.columns=this.getSort(value, step)
        }
        this.show = true;
      },

      onConfirm(value, index) {
        Toast(`当前值：${value}, 当前索引：${index}`);
        if(this.step==-1){
          this.options0_value=value
          // console.log(this.options0_value)
        }
           if(this.step==0){
          this.options1_value=value
          // console.log(this.options0_value)
        }
          if(this.step==1){
          this.options2_value=value
          // console.log(this.options0_value)
        }
           if(this.step==2){
          this.options3_value=value
          // console.log(this.options0_value)
        }
           if(this.step==3){
          this.options4_value=value
          // console.log(this.options0_value)
        }
           if(this.step==4){
          this.options5_value=value
          // console.log(this.options0_value)
        }
          if(this.step==5){
          this.options6_value=value
          // console.log(this.options0_value)
        }
           if(this.step==6){
          this.options7_value=value
          // console.log(this.options0_value)
        }
        this.show=false
      },
      onChange(picker, value, index) {
        Toast(`当前值：${value}, 当前索引：${index}`);
      },
      onCancel() {
        Toast('取消');
      },
      // 分类功能
      getLoadSortList() {
        this.axios
          .get("http://192.168.1.103:8083/knowledge/get_all_sort")
          .then((res) => {
            this.sorts.push(res.data)
            // console.log("所有分类")
            console.log(this.sorts)
            this.sort()
          });
      },
      // 分类第一级
      sort() {
        this.options0.push(this.sorts[0][0].sort_type_0)
        for (let i = 1; i < this.sorts[0].length; i++) {
          for (let j = 0; j < this.options0.length; j++) {
            if (this.sorts[0][i].sort_type_0 != this.options0[j] && j == this.options0.length -
              1) {
              this.options0.push(this.sorts[0][i].sort_type_0)
              break;
            }
            if (this.sorts[0][i].sort_type_0 == this.options0[j]) {
              break;
            }
          }
        }
        console.log(this.options0)
      },
      // 查询其他级分类列表
      getSort(value, step) {
        // console.log(step)
        if(step==-1){
           this.options0_value = ""
            this.options1_value = ""
          this.options2_value = ""
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
        }
        if (step == 0) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_0 == value) {
              options.push(this.sorts[0][i].sort_type_1)
              options_id.push(this.sorts[0][i].sort_id)

            }
          }
          // 使数据能显示
          this.options1 = Array.from(new Set(options))
          // this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          this.options1_value = ""
          this.options2_value = ""
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
          return this.options1
        }
        if (step == 1) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_1 == value) {
              options.push(this.sorts[0][i].sort_type_2)
              options_id.push(this.sorts[0][i].sort_id)
            }
          }
          this.options2 = Array.from(new Set(options))
          //   this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          // this.options2_value = ""
          // this.options3_value = ""
          // this.options4_value = ""
          // this.options5_value = ""
          // this.options6_value = ""
           return this.options2
        }
        if (step == 2) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_2 == value) {
              options.push(this.sorts[0][i].sort_type_3)
              options_id.push(this.sorts[0][i].sort_id)
            }
          }
          this.options3 = Array.from(new Set(options))
          //     this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          // this.options3_value = ""
          // this.options4_value = ""
          // this.options5_value = ""
          // this.options6_value = ""
           return this.options3
        }
        if (step == 3) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_3 == value) {
              options.push(this.sorts[0][i].sort_type_4)
              options_id.push(this.sorts[0][i].sort_id)
            }
          }
          this.options4 = Array.from(new Set(options))
          //    this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
           return this.options4
        }
        if (step == 4) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_4 == value) {
              options.push(this.sorts[0][i].sort_type_5)
              options_id.push(this.sorts[0][i].sort_id)
            }
          }
          this.options5 = Array.from(new Set(options))
          //    this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          this.options5_value = ""
          this.options6_value = ""
           return this.options5
        }
        if (step == 5) {
          let options = [];
          let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_5 == value) {
              options.push(this.sorts[0][i].sort_type_6)
              options_id.push(this.sorts[0][i].sort_id)
            }
          }
          this.options6 = Array.from(new Set(options))
          //    this.$store.commit("getSortIdList", options)
          // this.$store.commit("getSortIdListLength",options.length)
          this.options6_value = ""
           return this.options6
        }
        if (step == 6) {
          let options_id_last;
          //    let options_id = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_6 == value) {
              // options_id=this.sorts[0][i].sort_id
              options_id_last = this.sorts[0][i].sort_id
            }
          }
          //    this.$store.commit("getSortIdList",options_id)
          // this.$store.commit("getSortIdListLength",options_id.length)
          this.$store.commit("getSortIdListLast", options_id_last)

        }
      },
    },
    mounted() {
      // 分类功能
      this.getLoadSortList();
    }
  }
</script>

<style scoped>
.van-row{
  font-size: 15px;
  margin: 10px 0 10px 10px;
}
.van-col{
  margin: 5px 0;
}
</style>