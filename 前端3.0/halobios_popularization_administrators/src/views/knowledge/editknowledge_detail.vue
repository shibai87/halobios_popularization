<template>
    <div id="editKnowledge_detail">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            {{sort}}
            <el-form :model="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="科普知识名称">
                    <el-input v-model="ruleForm.knowledge_title" placeholder="请输入该科普知识的生物学名称" />
                </el-form-item>
                <el-form-item label="(已上传的)科普知识所属分类名" v-if="ruleForm.knowledge_sort_id != 0">
                    <div class="editKnowledge_sort">
                        {{ sort.sort_type_1 }}->{{ sort.sort_type_2 }}->{{ sort.sort_type_3 }}->{{ sort.sort_type_4
                        }}->{{ sort.sort_type_5 }}->{{ sort.sort_type_6 }}
                    </div>
                </el-form-item>
                <el-form-item label="(新编辑的)科普知识所属分类名">
                    <Sort></Sort>
                </el-form-item>
                <el-form-item label="科普知识分类内容" prop="knowledge_content">
                    <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)科普知识分类图片">
                    <div v-show="judge_knowledge" class="knowledge_img">
                        <img :src="Image()" />
                    </div>
                </el-form-item>
                <div class="knowledge_img_name">图片文件名： {{ ruleForm.knowledge_img }}</div>
                <el-form-item label="(新编辑的)科普知识分类图片">
                    <el-form-item label="(新编辑的)科普知识分类图片" prop="knowledge_img">
                        <el-upload class="upload-demo" ref="uploadRef" :action="action" :data="ruleForm"
                            :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                            :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                            :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList"
                             name="file" :auto-upload="false" accept="image/*">
                            <template #trigger>
                                <el-button v-if="judge_knowledge">重新上传图片</el-button>
                                <el-button v-else>选取文件</el-button>
                            </template>
                            <div slot="tip" class="el-upload__tip">只能上传一个文件</div>
                        </el-upload>
                    </el-form-item>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false" />
                <el-form-item>
                    <el-button @click="keepForm" v-show="!judge_knowledge">保存</el-button>
                    <el-button type="primary" @click="submitUpload()">提交</el-button>
                    <el-button @click="resetForm" v-show="!judge_knowledge">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue"
import { ref, reactive } from "vue"
import { ElMessage } from 'element-plus'
import Sort from "../../components/sort.vue"
import Constant from "../../assets/constant.js"
import KnowledgeService from "../../api/KnowledgeService.js"
import { useStore } from 'vuex'
import { useRouter } from "vue-router"
import type { UploadInstance } from 'element-plus'
import type { FormInstance} from 'element-plus'

const uploadRef = ref<UploadInstance>()
const store = useStore()
const router = useRouter()
const upload = ref('')
const ruleForm = ref<any>({})
const old_img_name = ref("")
const action = ref(Constant.URL + "/knowledge/updatasort")
// 判断是否有图片上传
const knowledge_img_name = ref("")
// 分类
const sort = ref({
    sort_type_1:"",
    sort_type_2:"",
    sort_type_3:"",
    sort_type_4:"",
    sort_type_5:"",
    sort_type_6:"",
})
// 详情
const fileList = reactive([]) //文件存储中间量
const judge_knowledge = ref(JSON.parse(sessionStorage.getItem('judgeKnowledge')||"0"))
const getSortListLastId = ref(store.state.sort_id_list_last)

onMounted(() => {
    KnowledgeService.get_knowledge(JSON.parse(sessionStorage.getItem('getKnowledge')||"0")).then((res) => {
        console.log(res)
        ruleForm.value = res[0]
        ruleForm.value.old_img_name = old_img_name.value;
        ruleForm.value.old_knowledge_sort_id = ruleForm.value.knowledge_sort_id
        if (ruleForm.value.old_knowledge_sort_id != 0) {
            getSort()
        }
    })
})
const getSort = () => {
    KnowledgeService.get_sort(ruleForm.value.knowledge_sort_id).then((res) => { sort.value = res[0] })
}
const goBack = () => router.push("/index")
const Image = () => Constant.URL + "/knowledge/" + ruleForm.value.knowledge_img;

const submitUpload = () => {
    if (getSortListLastId.value) {
        ruleForm.value.knowledge_sort_id = getSortListLastId.value
    } else {
        ruleForm.value.knowledge_sort_id = ruleForm.value.old_knowledge_sort_id
        console.log(getSortListLastId.value)
    }
    ruleForm.value.knowledge_upload = 1
                if (ruleForm.value.knowledge_title && ruleForm.value.knowledge_sort_id!=0 && ruleForm.value
                    .knowledge_content && knowledge_img_name.value) {
                        KnowledgeService.get_knowledgeTitle(ruleForm.value.knowledge_title).then((res) => {
                            if (res.msg == '查询成功'&&res.data!=JSON.parse(sessionStorage.getItem('getKnowledge')||"0")) {
                                ElMessage({
                                    message: "标题已存在，请重新为科普知识重新命名!",
                                    type: "warning",
                                });
                            } else {
                                console.log(ruleForm)
                                uploadRef.value!.submit()
                                router.push("/index")
                            }
                        })
                } else {
                    if (!knowledge_img_name.value) {
                        ElMessage({
                            message: "请确认已重新上传了图片!",
                            type: "warning",
                        });
                    } else {
                        ElMessage({
                            message: "请确认信息填写完毕!",
                            type: "warning",
                        });
                    }
                }
}
const keepForm=()=> {
    ruleForm.value.knowledge_sort_id=Number(ruleForm.value.knowledge_sort_id);
    if (getSortListLastId.value) {
        ruleForm.value.knowledge_sort_id = getSortListLastId.value
    }
    ruleForm.value.knowledge_upload= 0
                if (knowledge_img_name.value) {
                    uploadRef.value!.submit()
                } else {
                    ElMessage({
                        message: "请上传图片!",
                        type: "warning",
                    });
                }

            }
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
const handleExceed = () => {
    ElMessage.warning(`当前限制选择 1个文件`);
}
const beforeRemove = () => {
    // this.$confirm(`确定移除 ${file.name}？`);
    knowledge_img_name.value =""
}

const handleRemove = (file, fileList) => {
    console.log(file, fileList);
}
const handlePreview = (file) => {
    console.log(file);
    // this.judge_img=1
}
const handleChange = (file) => {
    knowledge_img_name.value = file.name
    console.log(knowledge_img_name);
}
const beforeUpload = (val) => {
    //    judge_img.value=1
    console.log(val);
}
const handleSuccess = () => {
    if (ruleForm.value.knowledge_upload == 0) {
        ElMessage.success("保存成功！");
    }
    else {
        ElMessage.success("上传成功！");
    }
}
const handleError = () => {
    if (ruleForm.value.knowledge_upload == 0) {
        ElMessage.success("保存失败！");
    }
    else {
        ElMessage.success("上传失败！");
    }
}
</script>

<style>
/* 布局 */
#editKnowledge_detail .el-header {
    background-color: rgb(99, 175, 255);
    padding: 20px 10px;
}

#editKnowledge_detail .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 660px;
}

/* 头部 */
#editKnowledge_detail .el-page-header__title {
    font-size: 25px;
}

#editKnowledge_detail .el-icon-back {
    font-size: 30px;
}

#editKnowledge_detail .el-page-header__content {
    font-size: 25px;
}

#editKnowledge_detail .upload-demo {
    width: 100px;
}

/* 主体 */
#editKnowledge_detail .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

#editKnowledge_detail .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

#editKnowledge_detail .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

#editKnowledge_detail .avatar {
    width: 178px;
    height: 178px;
    display: block;
}

#editKnowledge_detail .el-alert {
    height: 30px;
    width: 1200px;
    margin: 0 auto 2px;
}

#editKnowledge_detail .el-upload-list__item-name {
    width: 300px;
    text-align: left;
}
</style>
<style scoped>
/* 分类 */
.editKnowledge_sort {
    background-color: #fff;
    text-align: left;
    padding: 0 10px;
}

/* 图片 */
.knowledge_img {

    width: 100px;
    margin-left: 0;
    float: left;
}

.knowledge_img img {

    width: 100px;
    height: 100px;
}

.knowledge_img_name {
    text-align: left;
    margin: 0 220px 10px;
}
</style>