<template>
  <el-main height>
    <!-- Main content -->
    <!-- 查询卡片 -->
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
              <el-input size="small" style="width:440px"></el-input>
            </el-form-item>
            <el-form-item label="上架状态：">
              <el-input size="small" style="width:440px"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="10" class="form-search">
          <el-form>
            <el-form-item label="商品类别：">
              <el-select placeholder>
                <el-option></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商品品牌：">
              <el-select placeholder>
                <el-option></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
    <!-- 商品列表 -->
    <el-card :body-style="{ padding: '0px' }" style="margin-top:10px;">
      <div slot="header">
        <el-row>
          <el-col :span="12">
            <b style="font-size:18px;"><i class="fa fa-bars"></i><span>数据列表</span></b>
          </el-col>
          <el-col :span="12">
              <el-button size="small" style="float:right;" @click="addCommodity()">添加</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <!-- 显示数据列表 -->
      <el-table border stripe tooltip-effect="dark"  @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="编号">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="商品图片">
              <img src="" alt="">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="商品名称">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="商品价格">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="标签">
            <el-switch
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <el-switch
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </el-table-column>
          <el-table-column
              :prop="1"
              label="库存">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="品牌">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="类别">
          </el-table-column>
          <el-table-column
              :prop="1"
              label="操作">
              <el-row>
                  <el-col :span="12">
                      <el-button size="small">修改</el-button>
                  </el-col>
                  <el-col :span="12">
                      <el-button type="danger">删除</el-button>
                  </el-col>
              </el-row>
          </el-table-column>
      </el-table>
    </el-card>
      <el-row style="margin:10px;">
        <el-col :span="12">
          <el-form>
            <el-form-item>
              <el-select placeholder="批量操作" size="small">
                <el-option>
                </el-option>
              </el-select>
            <el-button type="primary" size="small">确定</el-button>
            </el-form-item>
            
          </el-form>
        </el-col>
        <el-col :span="12" style="float:right;">
          <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage3"
            :page-size="100"
            layout="prev, pager, next, jumper"
            :total="1000">
          </el-pagination>
        </div>
        </el-col>
      </el-row>        
  </el-main>
</template>
<script>
export default {
    data(){
        return{

        }
    },
    methods:{
      addCommodity(){
        this.$router.push("/commodity/add");
      }
    }
};
</script>
<style>
.form-search {
  margin: auto 10px;
}
</style>

