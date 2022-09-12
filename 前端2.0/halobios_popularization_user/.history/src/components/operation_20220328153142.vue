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
    .knowledge_details_main {
        text-align: center;
        margin: 60px auto;

    }

    .knowledge_details_main h1 {
        margin-top: 80px;
        font-size: 30px;
    }


    .knowledge_details_content {
        text-indent: 2em;
        font-size: 25px;
        margin: 10px;
        text-align: left;


    }

    .knowledge_details_knowledge_key {
        font-size: 20px;
        text-align: left;
        font-weight: bolder;
        margin: 10px;
    }

    /*  */
    #knowledge_details_other {
        margin: 0 20px;
    }

    /* sort_details_footer */
    .knowledge_details_footer {
        text-align: left;
        margin: 20px 10px;
    }

    .knowledge_details_footer h1 {
        margin-bottom: 20px;
    }

    .my_comment,
    .users_comment {
        margin-bottom: 10px;
    }

    /* .sort_comment_right{
        display: inline-block;
    } */
</style>