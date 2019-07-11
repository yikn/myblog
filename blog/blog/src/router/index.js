import Vue from 'vue'
import Router from 'vue-router'
import Test from '@/components/Test'
import Index from '@/components/Index/Index'
import BlogHeader from '@/components/Header/BlogHeader'
import MainContent from '@/components/Content/MainContent'
import CommonFooter from '@/components/Footer/CommonFooter'
import ArticleContent from '@/components/Article/ArticleContent'
import ArticleListContent from '@/components/Article/ArticleListContent'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        {
          path: '/',
          name: 'index',
          components: {
            header: BlogHeader,
            content: MainContent,
            footer: CommonFooter
          }
        },
        { // 文章详细内容
          path: 'article/:articleId',
          name: 'article',
          components: {
            header: BlogHeader,
            content: ArticleContent,
            footer: CommonFooter
          }
        },
        { // 文章列表
          path: 'articles',
          name: 'articles',
          components: {
            header: BlogHeader,
            content: ArticleListContent,
            footer: CommonFooter
          }
        },
        {
          path:'category/:categoryId',
          name: 'category',
          components: {
            header: BlogHeader,
            content: ArticleListContent,
            footer: CommonFooter
          }
        }]

    }
  ]
})

