<template>
    <el-main height="">
        <el-card :body-style="{ padding: '0px' }">
            <div slot="header">
                <el-row>
                    <el-col :span="12">
                        <b style="font-size:18px;"><i class="fa fa-search"></i><span>筛选搜索</span></b>
                    </el-col>
                    <el-col :span="12">
                        <el-button type="primary" style="float:right" size="small">查询结果</el-button>
                        <el-button style="float:right;margin-right:10px;" size="small">重置</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <el-row>
                <el-col :span="10" class="form-search">
                <el-form>
                    <el-form-item label="输入搜索：">
                    <el-input size="small" style="width:330px"></el-input>
                    </el-form-item>
                    <el-form-item label="申请时间：">
                        <el-date-picker
                        v-model="value1"
                        type="date"
                        placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
                </el-col>
                <el-col :span="10" class="form-search">
                <el-form>
                    <el-form-item label="处理状态：">
                    <el-select placeholder>
                        <el-option></el-option>
                    </el-select>
                    </el-form-item>
                    <el-form-item label="处理时间：">
                    <el-date-picker
                    type="date"
                    placeholder="选择日期">
                    </el-date-picker>
                    </el-form-item>
                </el-form>
                </el-col>
            </el-row>
        </el-card>

        <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
            <div slot="header">
                <b style="font-size:18px;"><i class="fa fa-bars"></i><span>数据列表</span></b>
            </div>
            <!-- card body -->
            <el-table border stripe>
                <el-table-column
                    :prop="1"
                    label="服务单号">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="申请时间">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="用户账号">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="退款金额">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="申请状态">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="处理时间">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="操作">
                    <el-button size="small"> 查看详情</el-button>
                    
                </el-table-column>
            </el-table>
            
        </el-card>
        
    </el-main>
</template>
<script>
export default {
    
}
</script>