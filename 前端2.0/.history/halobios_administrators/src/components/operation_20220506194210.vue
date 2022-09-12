<template>
    <div id="knowledge_details_other">
        <el-row  type="flex" class="row-bg" justify="space-around">
            <el-col :span="1">
                <el-badge :value="GoodNum" >
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-dianzan"></use>
                    </svg>
                </el-badge>
            </el-col>
            <el-col :span="1">
                <el-badge :value="StarNum">
                   <i class="el-icon-star-off"></i>
                </el-badge>
            </el-col>
            <el-col :span="1">
                <el-badge :value="CommentNum">
                     <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-pinglun"></use>
                    </svg>
                </el-badge>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import OperationService from "../api/OperationService.js"
    export default {
        name: 'knowledge_details_other',
        inject: ["reload"],
        data() {
            return {
                GoodNum: "",
                StarNum: "",
                CommentNum: "",
                good_color: "#000",
                star_color: "#000",
                good_id: "",
                star_id: "",
            }
        },
        methods: {
            getOperadionNumber() {
                    OperationService.getOperationNumber(this.getOperationSourceType,this.getSourceId,0).then((res) => {
                        this.GoodNum = res
                    })
                this.$axios.get("http://192.168.1.105:8083/operation/getOperationNumber?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId + "&operation_type=" + 1)
                    .then((res) => {
                        this.StarNum = res.data
                    })
                this.$axios.get("http://192.168.1.105:8083/operation/getOperationNumber?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId + "&operation_type=" + 2)
                    .then((res) => {
                        this.CommentNum = res.data
                    })
            },
        },
        computed: {
            getOperationSourceType() {
                return JSON.parse(sessionStorage.getItem('operation_source_type'))
            },
            getSourceId() {
                return JSON.parse(sessionStorage.getItem('source_id'))
            }
        },
        mounted() {
            this.getOperadionNumber()
        },
    }
</script>

<style>
    .el-icon-star-off:before{
        font-size: 48px;
    }
</style>
<style scoped>
    .icon {
        width: 3em;
        height: 3em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }


    /*  */
    #knowledge_details_other {
        margin: 0 20px;
    }
</style>