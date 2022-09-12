<template>
  <div id="sort">
        <!-- head -->
        <div id="sort_head">
           <div class="sort" v-for="(sort,index) in sorts" :key="index" @click="change_sort(sort.sort_id)">
     {{sort.sort_type_name}}
    </div>
        </div>
   
  </div>
</template>

<script>
export default {
  name: 'sort',
  data () {
    return {
      sorts:[
        {
          sort_id:1,
          sort_type_name:"浮游生物"
        },
         {
          sort_id:2,
          sort_type_name:"浮游生物"
        },
         {
          sort_id:3,
          sort_type_name:"浮游生物"
        },
          {
          sort_id:4,
          sort_type_name:"浮游生物"
        },
         {
          sort_id:5,
          sort_type_name:"浮游生物"
        },
         {
          sort_id:6,
          sort_type_name:"浮游生物"
        },
      ]
        
      
    }
  },
  methods: {
    change_sort(sort_id){
          alert.window(sort_id)
    }
  },
  mounted () {

  }
}
</script>

<style scoped>
.sort{
  display: inline-flex;
  
  margin: 10px;
  flex-wrap: wrap;

}
</style>
