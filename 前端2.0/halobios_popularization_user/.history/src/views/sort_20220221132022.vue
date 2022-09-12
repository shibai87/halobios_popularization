<template>
  <div id="sort">
        <!-- head -->
    <div id="sort_head" v-for="(sort,index) in sorts" :key="index">
     
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
          key:"浮游生物"
        },
         {
          key:"浮游生物"
        }, 
        {
          key:"浮游生物"
        }
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

</style>
