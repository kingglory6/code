<template>
    <el-main height="">
        <!-- Main content -->
        <!-- 订单搜索 -->
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
            <!-- 查询条件 -->
            <el-row>
                <el-col :span="10" class="form-search">
                <el-form>
                    <el-form-item label="输入搜索：">
                    <el-input size="small" style="width:330px" placeholder="订单编号"></el-input>
                    </el-form-item>
                    <el-form-item label="收件人：" >
                    <el-input size="small" style="width:330px"></el-input>
                    </el-form-item>
                </el-form>
                </el-col>
                <el-col :span="10" class="form-search">
                <el-form>
                    <el-form-item label="订单状态：">
                    <el-select placeholder>
                        <el-option v-for="(item, index) in orderStates" :key="index"
                        :label="item.label"
                        :value="item.value"
                        >

                        </el-option>
                    </el-select>
                    </el-form-item>
                    <el-form-item label="订单分类：">
                    <el-select placeholder>
                        <el-option></el-option>
                    </el-select>
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
            <!-- 订单数据 -->
            <el-table  border stripe  @selection-change="handleSelectionChange">
                <el-table-column
                type="selection"
                width="55">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="物流编号">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="下单时间">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="用户账号">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="订单金额">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="支付方式">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="订单状态">
                </el-table-column>
                <el-table-column
                    :prop="1"
                    label="操作">
                    <el-button size="small">查看订单</el-button>
                    <el-button size="small">订单发货</el-button>
                    <el-button size="small">查看物流</el-button>
                </el-table-column>
            </el-table>
            
        </el-card>
        
    </el-main>
</template>
<script>
export default {
    data() {
        return {
            orderStates:this.common.orderStates,
        }
    },
}
</script>
<style>
.form-search {
  margin: auto 10px;
}
</style>
