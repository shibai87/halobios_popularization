<template>
  <div>

     <ul class="sort">
       <!-- <li>q</li> -->
  <li v-for="(value,index) in sort_type_0" :key="index" class="sort_type">{{value}}</li>
  
      </ul>
  </div>
</template>


<script>
 
</script>


<style scoped>

.sort{
 display: inline-flex;
  margin: 0 auto;
  overflow-y: hidden;
  overflow-x: scroll;
  
}
.sort_type{
  font-size: 10px;
  width: 70px;
  height: 20px;
  
}
</style>
