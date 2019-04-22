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
            <el-button style="float:right;margin-right:10px;" size="small" @click="restart()">重置</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <!-- 查询条件 -->
      <el-row>
        <el-col :span="10" class="form-search">
          <el-form>
            <el-form-item label="输入搜索：">
              <el-input size="small" style="width:330px" placeholder="商品名称"></el-input>
            </el-form-item>
            <el-form-item label="上架状态：">
              <el-select placeholder="全部" size="small" v-model="search.shelf" >
                <el-option v-for="(item, index) in shelfStates" :key="index"
                :label="item.label"
                :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="10" class="form-search">
          <el-form>
            <el-form-item label="商品类别：">
              <el-select placeholder="全部" size="small" v-model="search.category">
                <el-option v-for="(item, index) in category" :key="index"
                :label="item.name"
                :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商品品牌：">
              <el-select placeholder="全部" size="small" v-model="search.brand">
                <el-option v-for="(item, index) in brand" :key="index"
                :label="item.name"
                :value="item.id"
                ></el-option>
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
      <el-table :data="commoditiesPageInfo.list" border stripe tooltip-effect="dark"  @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column
              prop="id"
              label="编号"
              width="55">
          </el-table-column>
          <el-table-column
              prop="title"
              label="商品名称">
          </el-table-column>
          <el-table-column
              prop="price"
              label="商品价格">
          </el-table-column>
          <el-table-column
              prop="description"
              label="描述">
          </el-table-column>
          <el-table-column
              label="上架" prop="shelf">
              <template scope="scope">
                <el-switch
                  v-model="scope.row.shelf"
                  @change="shelf(scope.$index)"
                  :active-value="1"
                  :inactive-value="0"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
                </el-switch>
              </template>
          </el-table-column>
          <el-table-column
            label="推荐" prop="recommend">  
            <template scope="scope">
              <el-switch
                v-model="scope.row.recommend"
                @change="recommend(scope.$index)"
                :active-value="1"
                :inactive-value="0"
                active-color="#13ce66"
                inactive-color="#ff4949">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column
              prop="stock"
              label="库存">
          </el-table-column>
          <el-table-column
              prop="brand.name"
              label="品牌">
          </el-table-column>
          <el-table-column
              prop="category.name"
              label="类别">
          </el-table-column>
          <el-table-column
              label="操作" width="180px;">
              <el-row>
                  <el-col :span="12">
                      <el-button size="small">修改</el-button>
                  </el-col>
                  <el-col :span="12">
                      <el-button type="danger" size="small">删除</el-button>
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
          <el-pagination
          @size-change="pageSizeChange()"
          @current-change="pageCurrentChange()"
          @prev-click="prev()"
          @next-click="next()"
          :current-page.sync="commoditiesPageInfo.pageNum"
          :page-sizes="[5, 10, 20, 40]"
          :page-size.sync="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="commoditiesPageInfo.total">
        </el-pagination>
        </el-col>
      </el-row>        
  </el-main>
</template>
<script>
export default {
    data(){
        return{
          // 商品信息
          commoditiesPageInfo:{},
          // 商品上架状态
          shelfStates:this.common.shelfStates,
          // 商品
          search:{
            shelf:{
              label:'全部',
              id:-1
            },
            category:{
              label:'全部',
              id:-1
            },
            brand:{
              label:'全部',
              id:-1
            }
          },
          // 所有分类
          category:[],
          // 所有品牌
          brand:[],
          // 每页显示数量
          pageSize:5
        }
    },
    methods:{
      addCommodity(){
        this.$router.push("/commodity/add");
      },
      handleSelectionChange(){

      },
      // 上一页
      prev(){
        if(this.commoditiesPageInfo.hasPreviousPage==true){
          this.commoditiesPageInfo.pageNum--;
          this.axios.get(this.common.httpAdminUrl+'/commodity/loadcommodity/'+this.commoditiesPageInfo.prePage,{
            params:{
              size:this.pageSize
            }
          })
          .then(res => {
            this.commoditiesPageInfo=res.data;
          })
          .catch(err => {
            console.error(err); 
          })
        }
      },
      // 下一页
      next(){
        if(this.commoditiesPageInfo.hasNextPage==true){
          this.commoditiesPageInfo.pageNum++;
          this.axios.get(this.common.httpAdminUrl+'/commodity/loadcommodity/'+this.commoditiesPageInfo.nextPage,{
            params:{
              size:this.pageSize
            }
          })
          .then(res => {
            this.commoditiesPageInfo=res.data;
          })
          .catch(err => {
            console.error(err); 
          })
        }
      },
      shelf(i){
        const param = new URLSearchParams();
        param.append('id',this.commoditiesPageInfo.list[i].id);
        param.append('option',this.commoditiesPageInfo.list[i].shelf);
        this.axios.post(this.common.httpAdminUrl+'/commodity/state',param)
        .then(res => {
          if(res.code!='ok'){
            this.$message({
              showClose: true,
              message: '修改失败',
              type: 'error'
            });
          }else{
            this.$message({
              showClose: true,
              message: '修改成功',
              type: 'success'
            });
          }
        })
        .catch(err => {
          console.error(err); 
        })
      },
      recommend(i){
        const param = new URLSearchParams();
        param.append('id',this.commoditiesPageInfo.list[i].id);
        param.append('type',this.commoditiesPageInfo.list[i].recommend);
        this.axios.post(this.common.httpAdminUrl+'/commodity/recommend',param)
        .then(res => {
          if(res.code!='ok'){
            this.$message({
              showClose: true,
              message: '修改失败',
              type: 'error'
            });
          }else{
            this.$message({
              showClose: true,
              message: '修改成功',
              type: 'success'
            });
          }
        })
        .catch(err => {
          console.error(err); 
        })
      },
      // 重置按钮
      restart(){
        this.search={
            shelf:{
              label:'全部',
              id:-1
            },
            category:{
              label:'全部',
              id:-1
            },
            brand:{
              label:'全部',
              id:-1
            }
          }
      },
      // 页数改变改变数据
      pageSizeChange(){
        this.init();
      },
      // 跳转到指定的页面
      pageCurrentChange(){
        this.axios.get(this.common.httpAdminUrl+'/commodity/loadcommodity/'+this.commoditiesPageInfo.pageNum,{
            params:{
              size:this.pageSize
            }
          })
          .then(res => {
            this.commoditiesPageInfo=res.data;
          })
          .catch(err => {
            console.error(err); 
          })
      },
      init(){
        // 发送请求初始化数据
        this.axios.get(this.common.httpAdminUrl+'/commodity/loadcommodity/1',{
          params:{
            size:this.pageSize
          }
        })
        .then(res => {
          this.commoditiesPageInfo=res.data;
        })
        .catch(err => {
          console.error(err); 
        })
        // 获取所有的类别
        this.axios.get(this.common.httpAdminUrl+'/commodity/category')
        .then(res => {
          this.category = res.data;
          this.category.push({
            id:-1,
            name:'全部'
          })
        })
        .catch(err => {
          console.error(err); 
        })
        // 获取所有品牌
        this.axios.get(this.common.httpAdminUrl+'/commodity/brand')
        .then(res => {
          this.brand = res.data;
          this.brand.push({
            id:-1,
            name:'全部'
          });
        })
        .catch(err => {
          console.error(err); 
        })
      }
    },
    mounted(){
      this.init()
    }
};
</script>
<style>
.form-search {
  margin: auto 10px;
}
</style>

