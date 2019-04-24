<template>
  <el-main height>
    <!-- Main content -->
    <!-- 秒杀活动 -->
    <el-card :body-style="{ padding: '0px' }">
      <div slot="header">
        <el-row>
          <el-col :span="12">
            <b style="font-size:18px;">
              <i class="fa fa-bars"></i>
              <span>秒杀活动数据列表</span>
            </b>
          </el-col>
          <el-col :span="12">
            <el-button style="float:right;" size="small" @click="addActive()">添加活动</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <!-- 折扣商品表 -->
      <el-table :data="commodityActivityList" border stripe>
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="commodity.title" label="商品名称"></el-table-column>
        <el-table-column prop="startTime" label="起始时间"></el-table-column>
        <el-table-column prop="endTime" label="终止时间"></el-table-column>
        <el-table-column prop="stock" label="库存"></el-table-column>
        <el-table-column prop="commodity.price" label="原价"></el-table-column>
        <el-table-column prop="price" label="折扣价"></el-table-column>
        <el-table-column prop="1" label="操作" width="180">
          <el-button type="text" size="small" @click="getDetailed()">查看详情</el-button>
          <el-button type="text" size="small">删除</el-button>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 添加活动dialog -->
    <el-dialog title="添加活动" :visible.sync="dialogVisible" width="50%">
      <el-form>
        <el-form-item label>
          <el-input
            v-model="searchCommodity"
            style="width:30%"
            placeholder="请输入需要搜索的商品"
            size="small"
          ></el-input>
          <el-button type="primary" size="small" @click="search()">
            <i class="fa fa-search"></i>
          </el-button>
        </el-form-item>
        <el-form-item label="请选择需要参与活动的商品">
          <el-select v-model="spikeCommodity.commodity.id" placeholder="请选择">
            <el-option
              v-for="(item, index) in commodityList"
              :key="index"
              :label="item.title"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="起始时间">
          <el-date-picker
            v-model="spikeCommodity.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker
            v-model="spikeCommodity.endTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="选择日期时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model="spikeCommodity.stock" placeholder="请输入" style="width:30%"></el-input>
        </el-form-item>
        <el-form-item label="折扣价">
          <el-input v-model="spikeCommodity.price" placeholder="请输入" style="width:30%"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendActivity()">确 定</el-button>
      </span>
    </el-dialog>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      // 所有活动商品
      commodityActivityList: [],
      // 所有商品列表
      commodityList: [],
      // 添加活动dialog
      dialogVisible: false,
      // 需要搜索的商品
      searchCommodity: "",
      // 需要添加的秒杀商品
      spikeCommodity: {
        type: 2,
        commodity: {},
        stock: 0,
        price: 0,
        startTime: "",
        endTime: ""
      }
    };
  },
  methods: {
    // 搜索商品
    search() {
      this.spikeCommodity.commodity = {};
      this.axios
        .get(this.common.httpAdminUrl + "/commodity/querycommodity/1", {
          params: {
            size: 0,
            shelf: -1,
            cid: -1,
            bid: -1,
            text: this.searchCommodity
          }
        })
        .then(res => {
          this.commodityList = res.data.list;
        })
        .catch(err => {
          console.error(err);
        });
      this.searchCommodity = "";
    },
    getDetailed() {},
    addActive() {
      this.dialogVisible = true;
    },
    // 添加商品活动
    sendActivity() {
      this.dialogVisible = false;
      this.axios
        .post(
          this.common.httpAdminUrl + "/commodity/activity",
          this.spikeCommodity
        )
        .then(res => {
          if (res.code == "ok") {
            this.$message({
              message: "添加成功",
              type: "info",
              showClose: true
            });
            this.init();
          } else if (res.code == "data exception") {
            this.$message({
              message: "数据异常",
              type: "warning",
              showClose: true
            });
          }
        })
        .catch(err => {
          console.error(err);
        });
    },
    // 初始化数据
    init() {
      // 获取所有的商品列表
      this.axios
        .get(this.common.httpAdminUrl + "/commodity/querycommodity/1", {
          params: {
            size: 0,
            shelf: -1,
            cid: -1,
            bid: -1,
            text: this.searchCommodity
          }
        })
        .then(res => {
          this.commodityList = res.data.list;
        })
        .catch(err => {
          console.error(err);
        });
      // 获取所有活动商品列表
      this.axios
        .get(this.common.httpAdminUrl + "/commodity/loadactivity", {
          params: {
            type: 2
          }
        })
        .then(res => {
          this.commodityActivityList = res.data;
        })
        .catch(err => {
          console.error(err);
        });
    }
  },
  mounted() {
    this.init();
  }
};
</script>
<style>
</style>

