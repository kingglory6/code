<template>
    <el-main height="">
        <!-- Main content -->
        <!-- 折扣活动 -->
        <el-card :body-style="{ padding: '0px' }">
            <div slot="header">
                <el-row>
                    <el-col :span="12">
                        <b style="font-size:18px;"><i class="fa fa-bars"></i><span>折扣活动数据列表</span></b>
                    </el-col>
                    <el-col :span="12"> 
                        <el-button style="float:right;" size="small">添加活动</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <!-- 折扣商品表 -->
            <el-table border stripe>
                <el-table-column 
                    :prop="1"
                    label="编号">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="活动名称">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="起始时间">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="终止时间">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="状态">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="操作">
                    <el-button type="text" size="small">设置商品</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                    <el-button type="text" size="small">删除</el-button>
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

