<template>
  <div>
    <van-notice-bar color="#1989fa" background="#ecf9ff" left-icon="info-o" speed=50 text="请按从左至右的顺序选择各级分类，并且请通过点击弹出框的确定按钮进行每一级分类的选择！"/>
    <van-button type="primary" hairline plain round @click="showPopup(0)">第零级 </van-button>
    <van-button type="primary" hairline plain round @click="showPopup(1)">第一级 </van-button>
    <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
      <van-picker title="请选择第零级分类" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel"
        @change="onChange"/>
    </van-popup>
    <div >{{this.options0_value}}---{{this.options1_value}}---{{this.options2_value}}---{{this.options3_value}}---{{this.options4_value}}---{{this.options5_value}}---{{this.options6_value}}</div>
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
        if(step==0){
              this.val=step,
              this.columns=this.options0
              this.getSort(this.options0_value, step)
        }else{
          this.getSort(this.options0_value, step)
        }

         if(step==1){
              this.val=step,
              this.columns=this.options0
              this.getSort(this.options0_value, step)
        }
       
      
        this.show = true;
      },

      onConfirm(value, index) {
        Toast(`当前值：${value}, 当前索引：${index}`);
        if(this.val==0){
          this.options0_value=value
          console.log(this.options0_value)
        }
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
          this.options2_value = ""
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
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
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
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

</style>