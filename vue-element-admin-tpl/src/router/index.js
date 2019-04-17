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
        {path: '/button', name: 'c-button', component: page('c-button')},
        {path: '/switch', name: 'c-switch', component: page('c-switch')},
        {path: '/checkbox', name: 'c-checkbox', component: page('c-checkbox')},
        {path: '/alert', name: 'c-alert', component: page('c-alert')},
        {path: '/input', name: 'c-input', component: page('c-input')},
        {path: '/keyboard', name: 'c-keyboard', component: page('c-keyboard')},
        {path: '/loading', name: 'c-loading', component: page('c-loading')},
        {path: '/data-table', name: 'p-data-table', component: page('table')},
        {path: '/dropdown', name: 'c-dropdown', component: page('c-dropdown')},
        {path: '/navbar', name: 'c-navbar', component: page('c-navbar')},
        {path: '/container', name: 'c-container', component: page('c-container')},
        {path: '/demo', name: 'demo', component: page('demo')},
        {path: '/404', name: '404', component: page('404')},
        {path: '/500', name: '500', component: page('500')},
        {path: '/commodity/add', name: 'commodity-add', component: page('commodity/add')},
        {path: '/commodity/brand', name: 'commodity-brand', component: page('commodity/brand')},
        {path: '/commodity/category', name: 'commodity-category', component: page('commodity/category')},
        {path: '/commodity/discount', name: 'commodity-discount', component: page('commodity/discount')},
        {path: '/commodity/list', name: 'commodity-list', component: page('commodity/list')},
        {path: '/commodity/spike', name: 'commodity-spike', component: page('commodity/spike')},
        {path: '/orders/cnode', name: 'cnode', component: page('cnode/index')},
        {path: '/orders/shipped', name: 'shipped', component: page('orders/shipped')},
        {path: '/orders/unshipped', name: 'unshipped', component: page('orders/unshipped')},
        {path: '/orders/received', name: 'received', component: page('orders/received')},
        {path: '/orders/unreceived', name: 'unreceived', component: page('orders/unreceived')},
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
