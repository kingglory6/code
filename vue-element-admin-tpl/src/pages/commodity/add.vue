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
                    <el-form-item label="*商品分类:">
                        <el-select placeholder="请选择" size="small">
                            <el-option v-for="(item, index) in category" :key="index"
                            :label="item.label">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="*商品名称:">
                        <el-input style="width:380px;" placeholder="请输入" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="*商品品牌:">
                        <el-select placeholder="请选择" size="small">
                            <el-option>
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品介绍:" >
                        <el-input style="width:380px;"
                        type="textarea"
                        autosize
                        clearable="true"
                        placeholder="请输入商品描述(不超过250字)">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="商品售价:">
                        <el-input style="width:380px;" size="small"></el-input>
                    </el-form-item>
                    <el-button type="primary" style="float:right;margin:auto 10px;" @click="next()">下一步</el-button>
                </el-form>
                
            </el-main>
            
            <!-- 添加商品第二步>>填写商品规格 -->
            <el-main height="" class="card-step" v-if="step==1">
                <!-- Main content -->
                <el-form ref="form">
                    <el-form-item label="添加商品规格:">
                        <el-input placeholder="添加规格信息" style="width:380px;"></el-input>
                        <el-button type="primary" >添加规格</el-button>
                    </el-form-item>
                </el-form>
                <el-table  border stripe>
                    <el-table-column
                        :prop="1"
                        label="规格">
                    </el-table-column>
                    <el-table-column
                        :prop="1"
                        label="图片">
                    </el-table-column>
                    <el-table-column
                        :prop="1"
                        label="操作">
                        <el-button type="primary" >修改</el-button>
                        <el-button type="primary" >删除</el-button>
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
                    <el-form-item label="商品分类:">
                        <el-select placeholder="请选择">
                            <el-option >
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="商品名称:" >
                        <el-input placeholder="请输入" style="width:380px;"></el-input>
                    </el-form-item>
                    <el-form-item label="商品品牌:">
                        <el-select placeholder="请选择">
                            <el-option >
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <el-form-item label="商品介绍:" >
                        <el-input style="width:380px;"
                        type="textarea"
                        autosize
                        clearable="true"
                        placeholder="请输入商品描述(不超过250字)">
                        </el-input>
                </el-form-item>
                <el-form-item label="商品售价:">
                    <el-input style="width:380px;" size="small"></el-input>
                </el-form-item>
                <label for="">商品规格:</label>
                <el-card :body-style="{ padding: '0px' }">
                     <el-table border stripe>
                         <el-table-column
                             :prop="1"
                             label="规格">
                         </el-table-column>
                         <el-table-column
                             :prop="1"
                             label="图片">
                         </el-table-column>
                         <el-table-column
                             :prop="1"
                             label="操作">
                            <el-button type="primary" size="small">修改图片</el-button>
                         </el-table-column>
                     </el-table>
                     
                </el-card>
                <el-button type="success" style="float:right;margin:10px;" >完成</el-button>
                <el-button style="float:right;margin:10px auto;" @click="prev()">上一步</el-button>
            </el-main>
            
        </el-card>
        
    </el-main>
</template>
<script>
export default {
    data(){
        return{
            // 步骤变量
            step:0,
            // 需要添加的商品对象
            commodity:{

            },
            category:this.common.category
        }
    },
    methods:{
        next(){
            this.step++;
        },
        prev(){
            this.step--;
        }
    },
    mounted(){
        this.axios.get('https://www.baidu.com')
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.error(err); 
        })
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

