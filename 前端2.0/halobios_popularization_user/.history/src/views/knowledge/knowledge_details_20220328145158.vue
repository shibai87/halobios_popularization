<template>
    <div id="knowledge_details">
            <van-nav-bar title="分类详情" fixed left-arrow  @click-left="pageBack"/>

        <div class="knowledge_details_main">
            <h1>{{ruleForm.knowledge_title}}</h1>
            <img class="knowledge_details_img" :src="knowledges[0].knowledge_img">
            <div class="knowledge_details_content">
               {{knowledges[0].knowledge_content}} 
            </div>
            <div class="knowledge_details_knowledge_key">
                关键词:{{knowledges[0].knowledge_key}}
            </div>

            <div class="knowledge_details_other">
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
        </div>

        <div class="knowledge_details_footer">
            <h1>评论</h1>
            <div class="my_comment">
                <!-- <div class="sort_comment_left">
                    <van-image class="comment_user_img" round width="100px" height="100px"
                        :src="my_comment[0].comment_user_img_src" />

                </div> -->
                <div class="knowledge_comment_right">
                    <van-field v-model="my_comment[0].message"  autosize :label="username" type="textarea" placeholder="请输入留言" />
                </div>
            </div>
            
             <div class="users_comment" v-for="(users_comment,index) in users_comments" :key="index">
                    <!-- <div class="sort_comment_left">
                        <van-image class="comment_user_img" round width="100px" height="100px"
                            :src="users_comment.comment_user_img_src" />

                    </div> -->
                    <div class="knowledge_comment_right">
                        <van-field v-model="users_comment.message" rows="3" autosize :label="username" type="textarea" placeholder="请输入留言" />
                    </div>
                </div>

        </div>

    </div>
</template>

<script>
    export default {
        name: '',
        data() {
            return {
                   ruleForm: {
                 knowledge_sort_id:"",
                    knowledge_title: '',
                    knowledge_content: '',
                    knowledge_img: "",
                },
                knowledges: [{
                    knowledge_title: "",
                    knowledge_img: require("../../assets/images/1.jpeg"),
                    knowledge_content: "其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%",
                    knowledge_key: "浮游生物"
                }],
                good_color: "#ff5000",
                star_color: "#ff5000",
                comment_color: "#ff5000",

                my_comment: [
                    {
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: ""
                    }

                ],
                username:"小莹",
                users_comments: [
                    {
                        username:"小莹1",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },
                     {
                         username:"小莹2",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },
                     {
                         username:"小莹3",
                        comment_user_img_src: require("../../assets/images/1.jpeg"),
                        message: "dfghjkll;khjgfd"
                    },

                ],
                

            }
        },
        methods: {
             pageBack() {
                window.history.back()
            },
             // 获取需编辑的知识详情
              getLoadKnowledge() {
                this.axios.get("http://192.168.1.103:8083/knowledge/get_knowledge?knowledge_id=" + this.getKnowledge)
                    .then((res) => {
                        console.log(res.data[0])
                         this.knowledges=res.data[0]
                          console.log( this.knowledges)
                        this.getSort()
                    });
            },
            getSort(){
                     this.axios.get("http://192.168.1.103:8083/knowledge/get_sort?knowledge_sort_id=" +this.ruleForm.knowledge_sort_id)
                    .then((res) => {
                        console.log(res.data[0])
                         this.sort=res.data[0]
                        //   console.log(this.ruleForm)
                    })
            },
            Image() {
            var url = "http://192.168.1.103:8083/knowledge/" +this.knowledges.knowledge_img
            return url
            },


            changeGoodColor() {

            },
            changeStarColor() {

            }
        },
        computed:{
            getKnowledge(){
              return JSON.parse(localStorage.getItem('knowledge_id'));
            },
             getSortListLastId() {
                return JSON.parse(localStorage.getItem('sort_id_list_last'));
            },

        },
        mounted(){
            this.getLoadKnowledge()
        }
    }
</script>

<style>
/*  */
#knowledge_details .van-nav-bar__content {
    height: 70px;
  }

#knowledge_details .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

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
/*  */
#knowledge_details .van-cell__title{
    font-size: 20px;
   
    text-align: center;
}
.van-field__control{
    font-size: 25px;
    line-height: 100px;
    text-align: center;
}
</style>
<style scoped>
/*  */
    .knowledge_details_main {
        text-align: center;
        margin:60px 10px;

    }
    .knowledge_details_main h1{
          margin-top: 80px;
        font-size: 30px;
    }
    .knowledge_details_img {
        height: 20%;
        width: 100%;
    }

    .knowledge_details_content {
        text-indent: 2em;
       font-size: 25px;
        width: 350px;
        

    }

    .knowledge_details_knowledge_key {
        font-size:20px ;
        text-align: left;
        font-weight:bolder;
    }
/*  */
    .knowledge_details_other {
        margin:0 20px;
    }

    /* sort_details_footer */
    .knowledge_details_footer {
        text-align: left;
        margin: 20px 10px;
    }
    .knowledge_details_footer h1{
        margin-bottom: 20px;
    }
    .my_comment,.users_comment{
        margin-bottom: 10px;
    }
    /* .sort_comment_right{
        display: inline-block;
    } */
    
   
</style>