<template>
    <el-main height="">
        <!-- Main content -->
        <!-- 品牌数据列表 -->
        <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
            <div slot="header">
                <el-row>
                    <el-col :span="12"><b style="font-size:18px;"><i class="fa fa-bars"></i><span>数据列表</span></b></el-col>
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
                    <el-button size="small">查看商品列表</el-button>
                    <el-button size="small">编辑</el-button>
                    <el-button size="small">删除</el-button>
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

