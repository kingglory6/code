<template>
    <el-main height="">
        <!-- Main content -->
        <!-- 参与折扣活动的商品 -->
        <el-card :body-style="{ padding: '0px' }">
            <div slot="header">
                <el-row>
                    <el-col :span="12">
                        <b style="font-size:18px;"><i class="fa fa-bars"></i><span>数据列表</span></b>
                    </el-col>
                    <el-col :span="12">
                        <el-button size="small" style="float:right;" @click="addActiveCommodity()">添加</el-button>
                    </el-col>
                </el-row>
            </div>
            <!-- card body -->
            <el-table  border stripe>
                <el-table-column 
                    :prop="1"
                    label="商品编号">
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
                    label="剩余数量">
                </el-table-column>
                <el-table-column 
                    :prop="1"
                    label="操作">

                </el-table-column>
            </el-table>
        <!-- 添加商品dialog -->
        <el-dialog
        title="选择商品"
        :visible.sync="addActiveCommodityDialog"
        width="50%"
        :before-close="handleClose">
        <el-form>
            <el-form-item>
                <el-input size="small" placeholder="商品名搜索"  style="width:30%"></el-input>
                <el-button size="small" class="fa fa-search" style="width:8%"></el-button>
            </el-form-item>
        </el-form>
        <el-table border stripe >
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column
                :prop="1"
                label="商品编号">
            </el-table-column>
            <el-table-column
                :prop="1"
                label="商品名称">
            </el-table-column>
            <el-table-column
                :prop="1"
                label="价格">
            </el-table-column>
        </el-table>
        <el-pagination
        background style="float:right;margin-top:5px;"
        layout="prev, pager, next"
        :total="1000">
        </el-pagination>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addActiveCommodityDialog = false">取 消</el-button>
            <el-button type="primary" @click="addActiveCommodityDialog = false">确 定</el-button>
        </span>
        </el-dialog>
        </el-card>
    </el-main>
</template>
<script>
export default {
    data(){
        return{
            addActiveCommodityDialog:false
        }
    },
    methods:{
        addActiveCommodity(){
            this.addActiveCommodityDialog=true;
        }
    }
}
</script>