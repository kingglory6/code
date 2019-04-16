const menus = [
  {id:'',icon:'fa fa-hand-o-up',label:'首页'},
  {
    id: 'commodity',
    icon: 'fa fa-microchip',
    label: '商品管理',
    submenu: [
      {
        id:'list',
        name:'commodity-list',
        label:'商品列表',
        icon: 'fa fa-hand-o-up'
      },{
        id:'add',
        name:'commodity-add',
        label:'添加商品',
        icon: 'fa fa-hand-o-up'
      },
      {
        id: 'type',
        label: '分类管理',
        icon: 'fa fa-hand-o-up',
        submenu: [
          {
            id: 'category',
            name: 'commodity-category',
            label: '类别',
            icon: 'fa fa-hand-o-up'
          },
          {
            id: 'brand',
            name:'commodity-brand',
            label: '品牌',
            icon: 'fa fa-hand-o-up'
          }
        ]
      },
      {
        id:'activity',
        label:'商品活动',
        icon:'fa fa-hand-o-up',
        submenu: [
          {
            id:'discount',
            name:'commodity-discount',
            label:'折扣活动',
            icon:'fa fa-hand-o-up'
          },
          {
            id:'spike',
            name:'commodity-spike',
            label:'秒杀活动',
            icon:'fa fa-hand-o-up'
          }
        ]
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
    id: 'statistics',
    icon: 'fa fa-paper-plane',
    label: '信息统计',
    submenu: [
      {id: 'income', name:'', label: '收入统计'},
      {id:'sales',name:'',label:'销量统计'}
    ]
  },
  {
    id: 'orders',
    icon: 'fa fa-paper-plane',
    label: '订单管理',
    submenu: [
      {id: 'shipped', name: 'shipped', label: '已发货'},
      {id: 'unshipped', name: 'unshipped', label: '未发货'},
      {id: 'received', name: 'received', label: '已收货'},
      {id: 'unreceived', name: 'unreceived', label: '待付款'},
    ]
  },
  {
    id: 'log',
    icon: 'fa fa-meetup',
    label: '日志统计'
  }
]
export default menus
