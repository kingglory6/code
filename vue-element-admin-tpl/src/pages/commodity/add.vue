<template>
    <el-main>
        <!-- 添加商品 -->
        <el-card :body-style="{ padding: '0px' }" class="card">
            <div slot="header">
                <el-steps :active="step" finish-status="success" align-center>
                    <el-step title="填写商品信息"></el-step>
                    <el-step title="填写商品规格"></el-step>
                    <el-step title="检查商品信息"></el-step>
                </el-steps>
            </div>
            <!-- card body -->
            <!-- 添加商品第一步>>填写商品基本信息 -->
            <el-main height="" class="card-step" v-if="step==0">
                <!-- 商品基本信息 -->
                <el-form ref="form">
                    <el-form-item label="*商品类别:">
                        <el-select placeholder="请选择" size="small"  v-model="commodity.category.id">
                            <el-option v-for="(item, index) in category" :key="index"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="*商品名称:">
                        <el-input style="width:380px;" placeholder="请输入" size="small" v-model="commodity.title"></el-input>
                    </el-form-item>
                    <el-form-item label="*商品品牌:">
                        <el-select placeholder="请选择" size="small"  v-model="commodity.brand.id">
                            <el-option v-for="(item, index) in brand" :key="index"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品介绍:" >
                        <el-input style="width:380px;" v-model="commodity.description"
                        type="textarea"
                        placeholder="请输入商品描述(不超过100字)">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="商品售价:">
                        <el-input style="width:380px;" size="small" v-model="commodity.price"></el-input>
                    </el-form-item>
                    <el-button type="primary" style="float:right;margin:auto 10px;" @click="next()">下一步</el-button>
                </el-form>
                
            </el-main>
            
            <!-- 添加商品第二步>>填写商品规格 -->
            <el-main height="" class="card-step" v-if="step==1">
                <!-- Main content -->
                <el-form ref="form">
                    <el-form-item label="添加商品规格:">
                        <el-input placeholder="添加规格信息" style="width:380px;" v-model="spec"></el-input>
                        <el-button type="primary" @click="addSpec()">添加规格</el-button>
                    </el-form-item>
                </el-form>
                <el-table :data="commodity.specList" border stripe>
                    <el-table-column
                        prop="param"
                        label="规格">
                    </el-table-column>
                    <el-table-column
                        prop="tempimg"
                        label="图片">
                        <template scope="scope">
                            <img :src="scope.row.tempimg" width="110" height="110">
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="操作">
                        <template scope="scope">
                            <el-row>
                                    <el-col :span="12">
                                        <el-upload
                                        class="upload-demo"
                                        action="http://192.168.43.29:8080/api/v1/admin/commodity/upimage"
                                        :on-success="upimageSuccess"
                                        :accept="'image/*'">
                                            <el-button type="primary" size="small" @click="uploading(scope.$index)">上传图片</el-button>
                                        </el-upload>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-button type="danger" size="small" @click="removeitem(scope.$index)">删除</el-button>
                                    </el-col>
                            </el-row>
                        </template>
                    </el-table-column>
                </el-table>
                
                <el-button type="primary" style="float:right;margin:10px;" @click="next()">下一步</el-button>
                <el-button style="float:right;margin:10px auto;" @click="prev()">上一步</el-button>
            </el-main>
            
            <!-- 填写商品第三步>>检查商品基本信息 -->
            <el-main height="" class="card-step" v-if="step==2">
                <!-- Main content -->
                <!-- 商品信息列表 -->
                <el-form>
                    <el-form-item label="商品类别:">
                        <el-select placeholder="请选择" v-model="commodity.category.id">
                            <el-option v-for="(item, index) in category" :key="index"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品名称:" >
                        <el-input placeholder="请输入" style="width:380px;" v-model="commodity.title"></el-input>
                    </el-form-item>
                    <el-form-item label="商品品牌:" >
                        <el-select placeholder="请选择" v-model="commodity.brand.id">
                            <el-option v-for="(item, index) in brand" :key="index"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品介绍:" >
                            <el-input style="width:380px;" v-model="commodity.description"
                            type="textarea"
                            placeholder="请输入商品描述(不超过250字)">
                            </el-input>
                    </el-form-item>
                    <el-form-item label="商品售价:">
                        <el-input style="width:380px;" size="small" v-model="commodity.price"></el-input>
                    </el-form-item>
                </el-form>
                <el-card :body-style="{ padding: '0px' }">
                     <el-table :data="commodity.specList" border stripe>
                         <el-table-column
                             prop="param"
                             label="规格" >
                         </el-table-column>
                         <el-table-column
                             prop="tempimg"
                             label="图片">
                             <template scope="scope">
                                <img :src="scope.row.tempimg" width="110" height="110">
                            </template>
                         </el-table-column>
                         <el-table-column
                             label="操作">
                                        <el-upload
                                        class="upload-demo"
                                        action="http://192.168.43.29:8080/api/v1/admin/commodity/upimage"
                                        :on-success="upimageSuccess"
                                        :accept="'image/*'">
                                            <el-button type="primary" size="small" @click="uploading(scope.$index)">修改图片</el-button>
                                        </el-upload>
                         </el-table-column>
                     </el-table>
                </el-card>
                <el-button type="success" style="float:right;margin:10px;" @click="sendCommodity()">完成</el-button>
                <el-button style="float:right;margin:10px auto;" @click="prev()">上一步</el-button>
            </el-main>
        </el-card>
    </el-main>
</template>
<script>
export default {
    data(){
        return{
            // 点击上传修改的item
            uploadItem:-1,
            // 步骤变量
            step:0,
            // 需要添加的商品对象
            commodity:{
                category:{
                },
                brand:{

                },
                price:0,
                specList:[]
            },
            category:[],
            brand:[],
            // 商品规格
            spec:'',
        }
    },
    methods:{
        // 移除规格选项
        removeitem(i){
            this.commodity.specList.splice(i,1);
        },
        uploading(i){
            this.uploadItem=i;
        },
        // 发送商品信息（添加商品）
        sendCommodity(){
            this.axios.post(this.common.httpAdminUrl+'/commodity/add',this.commodity)
            .then(res => {
                this.next();
                this.$message({
                    message: '添加成功',
                    type: 'success',
                    showClose: true,
                });
                this.commodity={
                    category:{
                    },
                    brand:{

                    },
                    price:0,
                    specList:[]
                }
            })
            .catch(err => {
                console.error(err); 
            })
        },
        next(){
            if(this.commodity.category!=null&this.commodity.brand!=null&this.commodity.title!=null){
                this.step++;
            }else{
                this.$message({
                    showClose: true,
                    message: '请完善您的信息',
                    type: 'error'
                });
            }
        },
        prev(){
            this.step--;
        },
        // 文件上传成功事件
        upimageSuccess(response, file, fileList){
            this.commodity.specList[this.uploadItem].img=this.common.picTrueUrl+response;
            this.commodity.specList[this.uploadItem].tempimg=this.common.picTempUrl+response;
            this.$message({
                message: '上传成功',
                type: 'success',
                showClose: true,
            });
        },
        // 添加规格事件
        addSpec(){
            if(this.spec.trim()!=''){
                this.commodity.specList.push({param:this.spec});
                this.spec='';
            }
        },
        // 初始化数据
        init(){
             // 获取所有的类别
            this.axios.get(this.common.httpAdminUrl+'/commodity/category')
            .then(res => {
                this.category = res.data;
            })
            .catch(err => {
            console.error(err); 
            })
            // 获取所有品牌
            this.axios.get(this.common.httpAdminUrl+'/commodity/brand')
            .then(res => {
                this.brand = res.data;
            })
            .catch(err => {
            console.error(err); 
            })
        }
    },
    mounted(){
        this.init();
    }
}
</script>
<style>
.card{
    margin: auto;
    width: 80%;
}
.card-step{
    margin:auto; 
    width:70%
}
</style>

