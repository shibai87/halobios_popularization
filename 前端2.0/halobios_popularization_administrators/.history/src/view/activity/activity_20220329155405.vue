<template>
    <div id="activity">
      <div v-if="activityList.length">
          
      </div>
         
        <div v-else>
            <el-empty description="无内容,可测试重新选择分类"></el-empty>
        </div>
    </div>
</template>

<script>
    export default {
        inject: ["reload"],
        name: "knowledge",
        data() {
            return {
                activityList: [],
            }
        },
        methods: {
        

        },
        computed: {
         
        },

        mounted() {

        }
    }
</script>
<style>
 
</style>
<style scoped>
 
</style>