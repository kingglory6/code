import Vue from 'vue'
import Router from 'vue-router'

import AppView from '@/components/app-view'
import Home from '@/pages/home'

Vue.use(Router)

const page = name => () => import('@/pages/' + name)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '',
      component: AppView,
      children: [
        {path: '/', name: 'home', component: Home},
        {path: '/404', name: '404', component: page('404')},
        {path: '/500', name: '500', component: page('500')},
        {path: '/commodity/add', name: 'commodity-add', component: page('commodity/add')},
        {path: '/commodity/brand', name: 'commodity-brand', component: page('commodity/brand')},
        {path: '/commodity/category', name: 'commodity-category', component: page('commodity/category')},
        {path: '/commodity/discount', name: 'commodity-discount', component: page('commodity/discount')},
        {path: '/commodity/list', name: 'commodity-list', component: page('commodity/list')},
        {path: '/commodity/spike', name: 'commodity-spike', component: page('commodity/spike')},
        {path: '/order/list', name: 'order-list', component: page('orders/list')},
        {path: '/order/back', name: 'order-back', component: page('orders/back')},
        {path: '/statistics/income', name: 'income', component: page('statistics/income')},
        {path: '/statistics/unreceived', name: 'sales', component: page('statistics/sales')},
      ]
    },
    {path: '/login', name: 'login', component: page('login')},
    // pages
    {path: '/home-login', name: 'p-login', component: page('home-login')},
    {path: '/register', name: 'p-register', component: page('register')},
    {path: '/keyboard', name: 'p-keyboard', component: page('c-keyboard')},
    {path: '*', redirect: {name: '404'}}
  ]
})
