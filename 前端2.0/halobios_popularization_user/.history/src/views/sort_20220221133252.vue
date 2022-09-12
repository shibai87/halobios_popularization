<template>
  <div id="sort">
        <!-- head -->
        <div id="sort_head">
           <div class="sort" v-for="(sort,index) in sorts" :key="index">
      
     {{sort}}
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
          "浮游生物",
          "浮游生物",
          "浮游生物",
          "浮游生物",
          "浮游生物",
          "浮游生物",
      ]
        
      
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
。sort{
  display: inline-flex;
  
  margin: 10px;
  flex-wrap: wrap;

}
</style>
