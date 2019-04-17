const menus = [
  {id:'home',name:'home',icon:'fa fa-home',label:'首页'},
  {
    id: 'commodity',
    icon: 'fa fa-shopping-bag',
    label: '商品管理',
    submenu: [
      {
        id:'commodity-list',
        name:'commodity-list',
        label:'商品列表',
        icon: 'fa fa-list-ul'
      },{
        id:'commodity-add',
        name:'commodity-add',
        label:'添加商品',
        icon: 'fa fa-plus-circle'
      },
      {
        id: 'type',
        label: '分类管理',
        icon: 'fa fa-th-large',
        submenu: [
          {
            id: 'commodity-category',
            name: 'commodity-category',
            label: '类别',
            icon: 'fa fa-th'
          },
          {
            id: 'commodity-brand',
            name:'commodity-brand',
            label: '品牌',
            icon: 'fa fa-tags'
          }
        ]
      },
      {
        id:'activity',
        label:'商品活动',
        icon:'fa fa-bookmark',
        submenu: [
          {
            id:'commodity-discount',
            name:'commodity-discount',
            label:'折扣活动',
            icon:'fa fa-usd'
          },
          {
            id:'commodity-spike',
            name:'commodity-spike',
            label:'秒杀活动',
            icon:'fa fa-clock-o'
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
    icon: 'fa fa-area-chart',
    label: '信息统计',
    submenu: [
      {id: 'income', name:'income',  label: '收入统计',icon:'fa fa-circle-o'},
      {id:'sales',name:'sales',label:'销量统计',icon:'fa fa-circle-o'}
    ]
  },
  {
    id: 'orders',
    icon: 'fa fa-calendar-minus-o',
    label: '订单管理',
    submenu: [
      {id: 'shipped', name: 'shipped', label: '已发货', icon:'fa fa-circle-o'},
      {id: 'unshipped', name: 'unshipped', label: '未发货', icon:'fa fa-circle-o'},
      {id: 'received', name: 'received', label: '已收货', icon:'fa fa-circle-o'},
      {id: 'unreceived', name: 'unreceived', label: '待付款', icon:'fa fa-circle-o'},
    ]
  },
  {
    id: 'log',
    icon: 'fa fa-file-o',
    label: '日志统计'
  }
]
export default menus
