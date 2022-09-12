<template>
    <div id="knowledge_details_other">
        <van-row>
            <van-col span="8">
                <van-icon name="good-job-o" :color="good_color" size="40" badge="5"
                    @click="changeGoodColor()" />
            </van-col>
            <van-col span="8">
                <van-icon name="star-o" :color="star_color" size="40" badge="5" @click="changeStarColor()" />
            </van-col>
            <van-col span="8">
                <van-icon name="comment-o" :color="comment_color" size="40" badge="5" />
            </van-col>
        </van-row>
    </div>
</template>

<script>
export default {
    name: 'knowledge_details_other',
    data() {
        return {
            good_color: "#ff5000",
            star_color: "#ff5000",
            comment_color: "#ff5000",
        }
    },
    methods: {
        changeGoodColor() {

        },
        changeStarColor() {

        }
    },
}
</script>

<style>
  
</style>
<style scoped>
    /*  */
    #knowledge_details_other {
        margin: 0 20px;
    }
</style>