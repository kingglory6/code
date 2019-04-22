<script>
// 这是一个全局的vue组件
const httpAdminUrl="http://192.168.43.29:8080/api/v1/admin"
const orderStates=[
    {
        label:'未发货',
        value:0
    },{
        label:'已发货',
        value:1
    },{
        label:'已收货',
        value:2
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
        value:0
    }
]
export default{
    httpAdminUrl,
    orderStates,
    shelfStates
}
</script>