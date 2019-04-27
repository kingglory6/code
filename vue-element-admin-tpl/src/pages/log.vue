<template>
    <el-main height="">
        <!-- Main content -->
        <el-form>
            <el-form-item>
                <el-date-picker
                v-model="dataTimeRange"
                type="datetimerange"
                :picker-options="pickerOptions"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd HH:mm:ss"
                align="right">
                </el-date-picker>
                <el-button type="primary" @click="search()">查 询</el-button>
            </el-form-item>            
        </el-form>
        <el-table :data="logInfo.list" border stripe>
            <el-table-column 
                prop="id"
                label="编号">
            </el-table-column>
            <el-table-column 
                prop="account"
                label="操作账号">
            </el-table-column>
            <el-table-column 
                prop="action"
                label="操作">
            </el-table-column>
            <el-table-column 
                prop="description"
                label="描述">
            </el-table-column>
            <el-table-column 
                prop="ip"
                label="ip">
            </el-table-column>
            <el-table-column 
                prop="result"
                :formatter="formatResult"
                label="结果">
            </el-table-column>
            <el-table-column 
                prop="time"
                label="操作时间">
            </el-table-column>
        </el-table>
        
        <el-pagination style="float:right"
        @size-change="pageSizeChange()"
        @current-change="pageCurrentChange()"
        :current-page.sync="logInfo.pageNum"
        :page-sizes="[50, 100, 200, 400]"
        :page-size.sync="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="logInfo.total">
        </el-pagination>
    </el-main>
    
</template>
<script>
export default {
    data() {
        return {
            // 选择的时间区间
            dataTimeRange:[],
            // 日志对象
            logInfo:{},
            // 快捷选择
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                    }
                }]
                },
            // 页面数据
            pageSize:50
        }
    },
    methods:{
        // 格式化结果
        formatResult(row, column, cellValue, index){
            return cellValue === true ? '成功':'失败';
        },
        pageSizeChange(){
            this.init();
        },
        // 当前页面改变
        pageCurrentChange(){
            this.axios.get(
            this.common.httpAdminUrl +
                "/log/loadlog/" +
                this.logInfo.pageNum,
            {
                params: {
                size: this.pageSize,
                startTime:this.dataTimeRange[0],
                endTime:this.dataTimeRange[1]
                }
            }
            )
            .then(res => {
            this.logInfo = res.data;
            })
            .catch(err => {
            console.error(err);
            });
        },
        // 检索数据
        search(){
            this.init();
        },
        // 初始化数据
        init(){
            this.axios.get(this.common.httpAdminUrl+'/log/loadlog/1',{
                params:{
                    size:this.pageSize,
                    startTime:this.dataTimeRange[0],
                    endTime:this.dataTimeRange[1]
                }
            })
            .then(res => {
                this.logInfo=res.data;
            })
            .catch(err => {
                console.error(err); 
            })
        }
    },
    mounted() {
        this.init();
    }
    
}
</script>