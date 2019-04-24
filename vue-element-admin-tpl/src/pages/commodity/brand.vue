<template>
  <el-main height>
    <!-- Main content -->
    <!-- 品牌数据列表 -->
    <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
      <div slot="header">
        <el-row>
          <el-col :span="12">
            <b style="font-size:18px;">
              <i class="fa fa-bars"></i>
              <span>数据列表</span>
            </b>
          </el-col>
          <el-col :span="12">
            <el-button size="small" style="float:right" @click="addBrand()">添加</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <el-table border stripe :data="brandList">
        <el-table-column prop="id" label="品牌编号"></el-table-column>
        <el-table-column prop="name" label="品牌名称"></el-table-column>
        <el-table-column prop="number" label="商品数量"></el-table-column>
        <el-table-column label="操作">
          <template scope="scope">
            <el-button size="small" @click="lookCommodity(scope.$index)">查看商品列表</el-button>
            <el-button size="small" type="danger" @click="deleteCommodity(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 添加商品品牌 -->
    <el-dialog title="添加商品" :visible.sync="addDialogVisible" width="50%">
      <el-form>
          <el-form-item label="品牌名称">
              <el-input v-model="brandname" style="width:30%"></el-input>
          </el-form-item>
      </el-form>
      
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendBrand()">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 商品dialog -->
    <el-dialog title="商品列表" :visible.sync="listDialogVisible" width="50%">
      <el-table :data="brand.commodityList" border stripe>
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="title" label="商品名"></el-table-column>
        <el-table-column prop="stock" label="库存"></el-table-column>
        <el-table-column prop="id" label="价格"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="listDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      addDialogVisible: false,
      listDialogVisible: false,
      // 所有品牌列表
      brandList: [],
      // 品牌商品列表
      brandCommodity: [],
      // 单个品牌对象
      brand: {},
      // 商品名    
      brandname:''
    };
  },
  methods: {
    // 发送品牌（添加）
    sendBrand(){
        this.addDialogVisible = false;
        this.axios.post(this.common.httpAdminUrl+'/commodity/createbrand',{
            name:this.brandname
        })
        .then(res => {
            if(res.code=='ok'){
                this.$message({
                    message: '创建成功',
                    type: 'info',
                    showClose: true,
                });
                this.init();
            }
        })
        .catch(err => {
            console.error(err); 
        })
    },
    // 添加品牌
    addBrand() {
        this.addDialogVisible=true;
    },
    // 查看商品列表
    lookCommodity(i) {
      this.listDialogVisible = true;
      this.brand = this.brandList[i];
    },
    // 删除品牌
    deleteCommodity(i) {
      if (confirm("是否删除改品牌")) {
        this.axios
          .delete(
            this.common.httpAdminUrl +
              "/commodity/removebrand/" +
              this.brandList[i].id
          )
          .then(res => {
            this.init();
            if (res.code == "ok") {
              this.$message({
                message: "删除成功",
                type: "success",
                showClose: true
              });
            } else {
              this.$message({
                message: "品牌下面还有商品存在，无法删除",
                type: "warning",
                showClose: true
              });
            }
          })
          .catch(err => {
            console.error(err);
          });
      }
    },
    // 初始化品牌数据
    init() {
      this.axios
        .get(this.common.httpAdminUrl + "/commodity/brand")
        .then(res => {
          this.brandList = res.data;
          for (let i = 0; i < this.brandList.length; i++) {
            this.brandList[i].number = this.brandList[i].commodityList.length;
          }
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

