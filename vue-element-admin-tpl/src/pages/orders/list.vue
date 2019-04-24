<template>
  <el-main height>
    <!-- Main content -->
    <!-- 订单搜索 -->
    <el-card :body-style="{ padding: '0px' }">
      <div slot="header">
        <el-row>
          <el-col :span="12">
            <b style="font-size:18px;">
              <i class="fa fa-search"></i>
              <span>筛选搜索</span>
            </b>
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
        <el-col :span="7" class="form-search">
          <el-form>
            <el-form-item label="输入搜索：">
              <el-input size="small" style="width:70%" placeholder="订单编号"></el-input>
            </el-form-item>
            <el-form-item label="收件人：">
              <el-input size="small" placeholder="请输入" style="width:70%"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="7" class="form-search">
          <el-form>
            <el-form-item label="订单状态：">
              <el-select size="small">
                <el-option
                  v-for="(item, index) in sendstatus"
                  :key="index"
                  :label="item.label"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="支付状态：">
              <el-select size="small">
                <el-option
                  v-for="(item, index) in payStates"
                  :key="index"
                  :label="item.label"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="7" class="form-search">
            <el-form>
                <el-form-item label="支付方式：" size="small">
                    <el-select placeholder="请选择" >
                        <el-option >
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </el-col>
      </el-row>
    </el-card>
    <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
      <div slot="header">
        <b style="font-size:18px;">
          <i class="fa fa-bars"></i>
          <span>数据列表</span>
        </b>
      </div>
      <!-- card body -->
      <!-- 订单数据 -->
      <el-table border stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column :prop="1" label="物流编号"></el-table-column>
        <el-table-column :prop="1" label="下单时间"></el-table-column>
        <el-table-column :prop="1" label="用户账号"></el-table-column>
        <el-table-column :prop="1" label="订单金额"></el-table-column>
        <el-table-column :prop="1" label="支付方式"></el-table-column>
        <el-table-column :prop="1" label="订单状态"></el-table-column>
        <el-table-column :prop="1" label="操作">
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
      // 发货状态
      sendstatus: this.common.sendstatus,
      // 支付状态
      payStates: this.common.payStates
    };
  }
};
</script>
<style>
.form-search {
  margin: auto 10px;
}
</style>
