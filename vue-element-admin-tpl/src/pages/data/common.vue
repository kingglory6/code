<script>
// 这是一个全局的vue组件
const httpAdminUrl="http://192.168.43.29:8080/api/v1/admin"
// 临时路径
const picTempUrl="http://192.168.43.29/temp/"
// 真实路径
const picTrueUrl="http://192.168.43.29/upload/"
const sendstatus=[
    {
        label:'未发货',
        id:0
    },{
        label:'已发货',
        id:1
    },{
        label:'已收货',
        id:2
    }
]
const payStates=[
    {
        label:'已支付',
        id:1
    },
    {
        label:'未支付',
        id:0
    },
    {
        label:'待支付',
        id:2
    }
]
const shelfStates=[
    {
        label:'已上架',
        id:1
    },
    {
        label:'未上架',
        id:0
    },
    {
        label:'全部',
        id:-1
    }
]
// 批量操作
const option=[
    {
        label:'',
        id:0
    }
]
export default{
    httpAdminUrl,
    sendstatus,
    shelfStates,
    picTempUrl,
    picTrueUrl,
    payStates
}
</script>