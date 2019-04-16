const menus = [
  {
    id: 'components',
    icon: 'fa fa-microchip',
    label: '商品管理',
    submenu: [
      {
        id: 'test',
        label: '分类管理',
        icon: 'fa fa-hand-o-up',
        submenu: [
          {
            id: 'test',
            label: '类别',
            icon: 'fa fa-hand-o-up'
          },
          {
            id: 'test',
            label: '品牌',
            icon: 'fa fa-hand-o-up'
          }
        ]
      },
      {
        id:'',
        label:'商品活动',
        icon:'fa fa-hand-o-up',
        submenu: [
          {
            id:'',
            label:'限时活动',
            icon:'fa fa-hand-o-up'
          },
          {
            id:'',
            label:'秒杀活动',
            icon:'fa fa-hand-o-up'
          }
        ]
      }
      ,{
        id:'',
        label:'商品显示',
        icon: 'fa fa-hand-o-up'
      }
    ]
  },
  {
    id: 'pages',
    icon: 'fa fa-circle-o',
    label: 'page页面',
    submenu: [
      {id: 'p-data-table', name: 'p-data-table', label: 'Table 表格数据'},
      {id: 'cnode', name: 'cnode', label: 'Cnode社区'},
      {id: 'p1', name: 'login', label: 'Login 登录'},
      {id: 'p2', name: 'p-login', label: 'Login 登录2'},
      {id: 'p3', name: 'p-register', label: 'Register 注册'},
      {id: '400', path: '/404', label: '404'},
      {id: '500', path: '/500', label: '500'}
    ]
  },
  {
    id: 'examples',
    icon: 'fa fa-paper-plane',
    label: '信息统计',
    submenu: [
      {id: 'es', name:'', label: 'Vuex 例子'}
    ]
  },
  {
    id: 'orders',
    icon: 'fa fa-paper-plane',
    label: '订单管理',
    submenu: [
      {id: 'e1', name: '', label: '已发货'},
      {id: 'e2', name: '', label: '未发货'},
      {id: 'e3', name: '', label: '已收货'},
      {id: 'e4', name: '', label: '待付款'},
    ]
  },
  {
    id: 'version',
    icon: 'fa fa-meetup',
    label: '日志统计'
  }
]
export default menus
