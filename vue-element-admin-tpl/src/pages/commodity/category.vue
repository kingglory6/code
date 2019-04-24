<template>
  <el-main height>
    <!-- Main content -->
    <!-- 类别管理 -->
    <el-card :body-style="{ padding: '0px' }">
      <div slot="header">
        <el-row>
          <el-col :span="12">
            <b style="font-size:18px;">
              <i class="fa fa-bars"></i>
              <span>数据列表</span>
            </b>
          </el-col>
          <el-col :span="12">
            <el-button style="float:right;" size="small" @click="addDialogVisible=true">添加</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <el-table :data="categoryCommodity" border stripe>
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="name" label="分类名称"></el-table-column>
        <el-table-column prop="number" label="商品数量"></el-table-column>
        <el-table-column label="操作">
          <template scope="scope">
            <el-button type="primary" size="small" @click="lookCommodity(scope.$index)">查看商品列表</el-button>
            <el-button type="danger" size="small" @click="deleteCategory(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 查看商品列表dialog -->
    <el-dialog title="商品列表" :visible.sync="listDialogVisible" width="50%">
      <el-table :data="category.commodityList" border stripe>
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="title" label="商品名"></el-table-column>
        <el-table-column prop="stock" label="库存"></el-table-column>
        <el-table-column prop="id" label="价格"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="listDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 添加商品分类dialog -->
    <el-dialog title="商品列表" :visible.sync="addDialogVisible" width="50%">
      <el-form>
        <el-form-item label="分类名称">
          <el-input v-model="categoryname" style="width:30%" size="small"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendCategory()">确 定</el-button>
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
      // 分类商品列表
      categoryCommodity: [],
      // 单个分类对象
      category: {},
      categoryname: ""
    };
  },
  methods: {
    // 删除分类信息
    deleteCategory(i) {
      let msg = "您真的确定要删除吗？"
      if(confirm(msg)==true){
        this.axios
          .delete(
            this.common.httpAdminUrl +
              "/commodity/removecategory/" +
              this.categoryCommodity[i].id
          )
          .then(res => {
            if(res.code=='ok'){
              this.init();
            }else{
              this.$message({
              message: '分类下面还有商品存在，无法删除',
              type: 'warning',
              showClose: true,
            });
            }
          })
          .catch(err => {
            
          });
      }
    },
    // 查看商品列表
    lookCommodity(i) {
      this.listDialogVisible = true;
      this.category = this.categoryCommodity[i];
    },
    // 添加分类
    sendCategory() {
      this.addDialogVisible = false;
          this.axios
            .post(this.common.httpAdminUrl + "/commodity/createcategory", {
              name: this.categoryname
            })
            .then(res => {
              if (res.code == "ok") {
                this.$message({
                  message: "添加成功",
                  type: "success",
                  showClose: true
                });
                this.init();
              }
            })
            .catch(err => {
              console.error(err);
            });
    },
    // 初始化数据
    init() {
      this.axios
        .get(this.common.httpAdminUrl + "/commodity/category")
        .then(res => {
          this.categoryCommodity = res.data;
          for (let i = 0; i < this.categoryCommodity.length; i++) {
            this.categoryCommodity[i].number = this.categoryCommodity[
              i
            ].commodityList.length;
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

