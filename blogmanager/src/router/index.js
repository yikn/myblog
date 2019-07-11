import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* eslint-disable */
/* Layout */
import layout from '@/layout/layout.vue'

const globalRoutes = [
  { path: '/404', component:() => import('@/components/404') , name: '404', meta: { title: '404未找到' } },
  { path: '/login',name:"login", component:() =>import('@/components/login'), name: 'login', meta: { title: '登录' } }
]

const constantRoutes = {
    path: '/',
    component: layout,
    redirect:'/article',
    children:[
      {
        path: '/article',
        name: 'article',
        component: () => import('@/views/table/articlelist'),
        },
        {
          path: '/postarticle',
          name: 'postArticle',
          component: () => import('@/views/form/article'),
        },
        {
          path: '/postarticle/:id',
          name: 'postArticle',
          component: () => import('@/views/form/article'),
        },
        {
          path: '/category',
          name: 'category',
          component: () => import('@/views/table/categorylist')
        },
        {
          path: '/setting',
          name: 'setting',
          component: () => import('@/views/form/setting')
        }
    ],
    beforeEnter (to, from, next) {
      let token = Vue.cookie.get('token')
      if (!token || !/\S/.test(token)) { // 正则：非空白就匹配
        next({ name: 'login' })
      }
      next()
    }
  }

const router = new Router({
  // mode: 'history', // require service support
  mode:'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: globalRoutes.concat(constantRoutes)
})


export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
