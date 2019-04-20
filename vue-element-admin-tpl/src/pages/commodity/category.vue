<template>
    <el-main height="">
        <!-- Main content -->
        <!-- 类别管理 -->
        <el-card :body-style="{ padding: '0px' }">
            <div slot="header">
                <el-row>
                    <el-col :span="12"><b style="font-size:18px;"><i class="fa fa-bars"></i><span>数据列表</span></b></el-col>
                    <el-col :span="12"><el-button style="float:right;" size="small">添加分类</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <el-table border stripe>
                <el-table-column 
                    :prop="1"
                    label="编号">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="分类名称">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="商品数量">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="操作">
                    <el-button type="primary" size="small">查看商品列表</el-button>
                    <el-button size="small">编辑</el-button>
                    <el-button type="danger">删除</el-button>
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

