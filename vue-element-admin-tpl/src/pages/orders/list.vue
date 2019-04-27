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
            <el-button type="primary" style="float:right" size="small" @click="search()">查询结果</el-button>
            <el-button style="float:right;margin-right:10px;" size="small" @click="reset()">重置</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- card body -->
      <!-- 查询条件 -->
      <el-row>
        <el-col :span="7" class="form-search">
          <el-form>
            <el-form-item label="输入搜索：">
              <el-input size="small" style="width:70%" placeholder="订单编号" v-model="orderSearch.wl"></el-input>
            </el-form-item>
            <el-form-item label="收件人：">
              <el-input size="small" placeholder="请输入" style="width:70%" v-model="orderSearch.name"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="7" class="form-search">
          <el-form>
            <el-form-item label="订单状态：">
              <el-select size="small" v-model="orderSearch.send">
                <el-option
                  v-for="(item, index) in sendstatus"
                  :key="index"
                  :label="item.label"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="支付状态：">
              <el-select size="small" v-model="orderSearch.paystatu">
                <el-option
                  v-for="(item, index) in payStatus"
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
                <el-form-item label="支付方式：">
                    <el-select placeholder="请选择" size="small" v-model="orderSearch.payway">
                        <el-option v-for="(item, index) in payWay" :key="index"
                        :label="item.label"
                        :value="item.id">
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
      <el-table :data="ordersInfo.list" border stripe>
        <el-table-column prop="wuliu" label="物流编号" width="180"></el-table-column>
        <el-table-column prop="time" label="下单时间"></el-table-column>
        <el-table-column prop="customer.name" label="购买用户"></el-table-column>
        <el-table-column prop="total" label="订单金额"></el-table-column>
        <el-table-column prop="payway" :formatter="formatPayway" label="支付方式"></el-table-column>
        <el-table-column prop="sendStatus" :formatter="formatsendStatus" label="订单状态"></el-table-column>
        <el-table-column prop="payStatus" :formatter="formatpayStatus" label="支付状态"></el-table-column>
        
        <el-table-column label="操作" width="320">
          <template scope="scope">
            <el-button size="small" @click="lookOrder(scope.$index)">查看订单</el-button>
            <el-button size="small" @click="changeOrder(scope.$index)" v-if="ordersInfo.list[scope.$index].sendStatus==0&&ordersInfo.list[scope.$index].payStatus==1">订单发货</el-button>
            <el-button size="small" @click="lookwl(scope.$index)" v-if="ordersInfo.list[scope.$index].sendStatus>=1">查看物流</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分页 -->
    <el-pagination style="float:right;"
      @size-change="pageSizeChange()"
      @current-change="pageCurrentChange()"
      @prev-click="prev()"
      @next-click="next()"
      :current-page.sync="ordersInfo.pageNum"
      :page-sizes="[5, 10, 20, 30]"
      :page-size.sync="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="ordersInfo.total" background>
    </el-pagination>
    <!-- 查看订单dialog -->
    <el-dialog
      title="查看订单"
      :visible.sync="orderDialogVisible"
      width="70%">

      <el-form label-width="80px">
        <el-form-item label="收货地址">
          <el-input size="small" v-if="ordersInfo.list.length!=0" v-model="ordersInfo.list[item].address"></el-input>
        </el-form-item>
        <el-form-item label="收件人">
          <el-input size="small" v-if="ordersInfo.list.length!=0" v-model="ordersInfo.list[item].name"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input size="small" v-if="ordersInfo.list.length!=0" v-model="ordersInfo.list[item].phone"></el-input>
        </el-form-item>
      </el-form>
      
      <!-- 订单商品 -->
      <el-table v-if="ordersInfo.list.length!=0" :data="ordersInfo.list[item].item" border stripe>
        <el-table-column 
          prop="commodity.id"
          label="编号">
        </el-table-column>
        <el-table-column 
          prop="commodity.title"
          label="商品">
        </el-table-column>
        <el-table-column 
          prop="quantity"
          label="数量">
        </el-table-column>
        <el-table-column 
          prop="remark"
          label="备注">
        </el-table-column>
        <el-table-column 
          prop="spec.param"
          label="规格">
        </el-table-column>
      </el-table>
      
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sendOrder()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 查看物流dialog -->
    <el-dialog
      title="查看物流" 
      :visible.sync="wlDialogVisible"
      width="70%">
      <!-- 物流信息 -->
      <div >
        <h3>{{wuliu.expName}}</h3>
        <el-steps direction="vertical" :active="0">
          <el-step v-for="(item, index) in wuliu.list" :key="index" :title="item.status" :description="item.time" icon="fa fa-truck"></el-step>
        </el-steps>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="wlDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    
    <!-- 订单发货dialog -->
    <el-dialog
      title="订单发货"
      :visible.sync="sendDialogVisible"
      width="50%">
      <el-form label-width="80px">
        <el-form-item label="订单编号">
          <el-input v-model="orderNum"></el-input>
        </el-form-item>
      </el-form>
      
      <span slot="footer" class="dialog-footer">
        <el-button @click="sendDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendCommodity()">发 货</el-button>
      </span>
    </el-dialog>
  </el-main>
</template>
<script>
export default {
  data() {
    return {
      item:0,
      wlDialogVisible:false,
      orderDialogVisible:false,
      sendDialogVisible:false,
      // 订单对象
      ordersInfo:{},
      // 发货状态
      sendstatus: this.common.sendstatus,
      // 支付状态
      payStatus: this.common.payStatus,
      // 支付方式
      payWay:this.common.payWay,
      orderSearch:{
        size:5,
        wl:'',
        // 收件人
        name:'',
        // 支付状态
        paystatu:-1,
        // 订单状态
        send:-1,
        payway:-1
      },
      pageSize:5,
      // 订单编号
      orderNum:'',
      wuliu:{}
    };
  },
  methods: {
    // 订单发货（发货）
    sendCommodity(){
      this.sendDialogVisible = false;
      const param = new URLSearchParams();
      param.append('id',this.ordersInfo.list[this.item].id);
      param.append('wuliu',this.orderNum);
      this.axios.put(this.common.httpAdminUrl+'/orders/sendhuo',param)
      .then(res => {
        if(res.code == 'ok'){
          this.$message({
            message: '修改成功',
            type: 'success',
            showClose: true,
          });
        }
      })
      .catch(err => {
        console.error(err); 
      })
    },
    // 发送订单（修改）
    sendOrder(){
      this.orderDialogVisible = false;
      if(this.ordersInfo.list[this.item].sendStatus==0){
        this.axios.put(this.common.httpAdminUrl+'/orders/saveorder',{
            id:this.ordersInfo.list[this.item].id,
            name:this.ordersInfo.list[this.item].name,
            phone:this.ordersInfo.list[this.item].phone,
            address:this.ordersInfo.list[this.item].address
          })
        .then(res => {
          if(res.code=='ok'){
            this.$message({
              message: '修改成功',
              type: 'success',
              showClose: true,
            });
          }
        })
        .catch(err => {
          console.error(err); 
        })
      }else{
        this.$message({
          message: '订单已发货，无法修改订单',
          type: 'warning',
          showClose: true,
        });
        
      }
    },
    // 查看物流
    lookwl(i){
      this.wlDialogVisible=true;
      this.item=i;
      this.axios.get(this.common.httpAdminUrl+'/commodity/wuliu',{
        params:{
          no:this.ordersInfo.list[this.item].wuliu
        }
      })
      .then(res => {
        this.wuliu=res.result;
      })
      .catch(err => {
        console.error(err); 
      })
    },
    // 订单发货
    changeOrder(i){
      this.sendDialogVisible=true;
      this.item=i;
    },
    // 查看订单
    lookOrder(i){
      this.orderDialogVisible=true;
      this.item=i;
    },
    // 上一页
    prev(){
      if(this.ordersInfo.hasPreviousPage==true){
        this.ordersInfo.pageNum--;
        this.axios
          .get(
            this.common.httpAdminUrl +
              "/commodity/querycommodity/" +
              this.ordersInfo.prePage,
            {
              params: {
                size:this.pageSize,
                wl:this.orderSearch.wl,
                // 收件人
                name:this.orderSearch.name,
                // 支付状态
                paystatu:this.orderSearch.paystatu,
                // 订单状态
                send:this.orderSearch.send,
                payway:this.orderSearch.payway
              }
            }
          )
          .then(res => {
            this.commoditiesPageInfo = res.data;
          })
          .catch(err => {
            console.error(err);
          });
      }
    },
    // 下一页
    next(){
      if(this.ordersInfo.hasNextPage==true){
        this.ordersInfo.pageNum++;
        this.axios
          .get(
            this.common.httpAdminUrl +
              "/commodity/querycommodity/" +
              this.ordersInfo.nextPage,
            {
              params: {
                size:this.pageSize,
                wl:this.orderSearch.wl,
                // 收件人
                name:this.orderSearch.name,
                // 支付状态
                paystatu:this.orderSearch.paystatu,
                // 订单状态
                send:this.orderSearch.send,
                payway:this.orderSearch.payway
              }
            }
          )
          .then(res => {
            this.commoditiesPageInfo = res.data;
          })
          .catch(err => {
            console.error(err);
          });
      }
    },
    // 跳转到指定的页面
    pageCurrentChange(){
      this.axios.get(this.common.httpAdminUrl+'/orders/loadorders/'+this.ordersInfo.pageNum,{
          params:{
            size:this.pageSize,
            wl:this.orderSearch.wl,
            // 收件人
            name:this.orderSearch.name,
            // 支付状态
            paystatu:this.orderSearch.paystatu,
            // 订单状态
            send:this.orderSearch.send,
            payway:this.orderSearch.payway
          }
        })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.error(err); 
      })
    },
    // 页面数据改变
    pageSizeChange(){
      this.init()
    },
    // 格式化支付方式
    formatPayway(row, column, cellValue, index){
      return cellValue === 1 ? '支付宝' : cellValue === 0 ? '微信' : '未支付';
    },
    // 格式化订单状态 
    formatsendStatus(row, column, cellValue, index){
      return cellValue === 1 ? '已发货': cellValue === 2?'已收货':'未发货';
    },
    // 格式化支付状态 
    formatpayStatus(row, column, cellValue, index){
      return cellValue === 1 ? '已支付' :  cellValue === 2?'未支付' : '待支付';
    },
    // 条件查询
    search(){
      this.axios.get(this.common.httpAdminUrl+'/orders/loadorders/1',{
        params:{
          size:this.pageSize,
          wl:this.orderSearch.wl,
          // 收件人
          name:this.orderSearch.name,
          // 支付状态
          paystatu:this.orderSearch.paystatu,
          // 订单状态
          send:this.orderSearch.send,
          payway:this.orderSearch.payway
        }
      })
      .then(res => {
        this.ordersInfo = res.data
      })
      .catch(err => {
        console.error(err); 
      })
    },
    // 重置
    reset(){
      this.orderSearch={
          size:0,
          wl:'',
          // 收件人
          name:'',
          // 支付状态
          paystatu:-1,
          // 订单状态
          send:-1,
          payway:-1
      }
    },
    // 初始化数据
    init(){
      this.axios.get(this.common.httpAdminUrl+'/orders/loadorders/1',{
        params:{
          size:this.pageSize,
          paystatu:-1,
          payway:-1,
          send:-1
        }
      })
      .then(res => {
        this.ordersInfo=res.data;
      })
      .catch(err => {
        console.error(err); 
      })
    }
  },
  mounted() {
    this.init();
  },
};
</script>
<style>
.form-search {
  margin: auto 10px;
}
</style>
