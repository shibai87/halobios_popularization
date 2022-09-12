<template>
        <div class="knowledge_details_footer">
            <h1>评论</h1>
            <div class="my_comment">
                <!-- <div class="sort_comment_left">
                    <van-image class="comment_user_img" round width="100px" height="100px"
                        :src="my_comment[0].comment_user_img_src" />

                </div> -->
                <div class="knowledge_comment_right">
                    <van-field v-model="my_comment[0].message" autosize :label="username" type="textarea"
                        placeholder="请输入留言" />
                </div>
            </div>

            <div class="users_comment" v-for="(users_comment,index) in users_comments" :key="index">
                <!-- <div class="sort_comment_left">
                        <van-image class="comment_user_img" round width="100px" height="100px"
                            :src="users_comment.comment_user_img_src" />

                    </div> -->
                <div class="knowledge_comment_right">
                    <van-field v-model="users_comment.message" rows="3" autosize :label="username" type="textarea"
                        placeholder="请输入留言" />
                </div>
            </div>
        </div>
</template>

<script>
    export default {
        name: '',
        data() {
            return {

                my_comment: [{
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: ""
                    }

                ],
                username: "小莹",
                users_comments: [{
                        username: "小莹1",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },
                    {
                        username: "小莹2",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },
                    {
                        username: "小莹3",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },

                ],


            }
        },
    }
</script>

<style>
    /*  */
      #knowledge_details .van-nav-bar__title {
        height: 30px;
        line-height: 30px;
      font-size: 20px;
      text-align: center;

    }
    #knowledge_details .van-nav-bar__content {
        height: 70px;
    }

  

    #knowledge_details .van-nav-bar__right {
        right: 5px;
        font-size: 20px;
    }

    #knowledge_details .van-icon {
        color: #000;
        font-size: 30px;
        margin-right: 5px;
    }

 #knowledge_details .knowledge_details_knowledge_key .van-col {
    overflow: scroll;
    white-space: nowrap;
  }
    #knowledge_details .van-field__control {
        font-size: 25px;
        line-height: 100px;
        text-align: center;
    }
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
    .knowledge_details_other {
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