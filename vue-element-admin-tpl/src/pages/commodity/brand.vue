<template>
    <el-main height="">
        <!-- Main content -->
        <el-card :body-style="{ padding: '0px' }">
            <div slot="header">
                <el-row>
                    <el-col :span="12"><span>筛选搜索</span></el-col>
                    <el-col :span="12"><el-button type="primary" size="small" style="float:right;">查询结果</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <!-- 筛选搜索品牌 -->
            <el-main height="">
                <!-- Main content -->
                <el-form >
                    <el-form-item label="筛选搜索:">
                        <el-input placeholder="品牌名称/关键字" style="width:550px;"></el-input>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-card>
        <!-- 品牌数据列表 -->
        <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
            <div slot="header">
                <el-row>
                    <el-col :span="12"><span>数据列表</span></el-col>
                    <el-col :span="12"><el-button size="small" style="float:right">添加</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <el-table border stripe @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="品牌编号">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="品牌名称">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="商品数量">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="操作">
                </el-table-column>
            </el-table>
            
        </el-card>
        
    </el-main>
    
</template>
<script>
export default {
    data(){
        return{
            
        }
    }
}
</script>
<style>

</style>

