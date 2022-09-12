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

</style>
<style scoped>
    

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