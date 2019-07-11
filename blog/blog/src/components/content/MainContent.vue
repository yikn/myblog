<template>
  <div class="home-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
        <div class="layout-left">
          <section-title :mainTitle="'文章'" :subTitle="'Articles'" :tipText="'View More'" :tipHref="'/'">
          </section-title>
          <article-list-cell v-for="article in articleList" :article="article" :key="article.title" :type="'article'"></article-list-cell>
          <iv-page
          @on-change="handlePageChange"
          :currentn="current"
          :total="total"
          :page-size="size">
          </iv-page>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <about></about>
          <div class="tree">
            <h3><i class="c-icon"></i>分类</h3>
            <ptree v-for="(item,index) in categoryList" :key="index" :model="item"></ptree>
          </div>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import About from '@/components/views/About'
import SectionTitle from '@/components/section/SectionTitle'
import TitleMenuFilter from '@/components/section/TitleMenuFilter'
import ArticleListCell from '@/components/views/ArticleListCell'
import Classify from '@/components/classify/Classify'
import Ctree from '@/components/classify/Ctree'
import merge from 'lodash/merge'
import {DefaultLimitSize} from '@/common/js/const'

export default {
  data () {
    return {
      articleList: [],
      categoryList: [],
      selected_category: this.$route.params.id,
      currentPage: 1,
      pageSize: DefaultLimitSize,
      categoryId: this.$route.params.id,
      menuParams: {},
      noMoreData: false,
      testdata: '',
      total:0,
      current:1,
      size:5
    }
  },
  created () {
    this.listArticle()
    this.listCategory()
  },
  methods: {
    handlePageChange(current){
      this.current=current
      console.log(this.current)
      this.listArticle()
    },
    listArticle () {
      let params = {
        categoryId: this.categoryId,
        current:this.current,
        size:this.size
      }
      params = merge(params, this.menuParams)
      this.$http({
        url: '/api/articles',
        params: this.$http.adornParams(params),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.articleList = data.page
          this.total =data.total
        }
      })
    },
    listCategory () {
      this.$http({
        url: '/api/category',
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = data.categoryList
        }
      })
    },
    test () {
      this.$http({
        url: '/api/test',
        method: 'get'
      }).then(({data}) => {
        console.log(data)
        this.testdata = data
      })
    },
    filterByMenu (params) {
      this.resetCurrentPage()
      this.menuParams = params
      this.listArticle()
    },
    filterByCategory (params) {
      this.resetCurrentPage()
      this.categoryId = params
      this.listArticle()
    },
    resetCurrentPage () {
      this.currentPage = 1
    },
    browseMore () {
      this.currentPage++
      let params = {
        categoryId: this.categoryId,
        limit: this.pageSize,
        page: this.currentPage
      }
      params = merge(params, this.menuParams)
      this.$http({
        url: this.$http.adornUrl('/articles'),
        params: this.$http.adornParams(params),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          if (data.page.totalPage <= data.page.currPage) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.articleList = this.articleList.concat(data.page.list)
        }
      }).then(response => {
        this.$refs.browseMore.stopLoading()
      }).catch(error => {
        this.$refs.browseMore.stopLoading()
        console.log(error)
      })
    }
  },
  components: {
    'about': About,
    'article-list-cell': ArticleListCell,
    'section-title': SectionTitle,
    'title-menu-filter': TitleMenuFilter,
    'class': Classify,
    'ptree': Ctree
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
@import "../../common/stylus/theme.styl";
  .home-content
    width auto
    @media only screen and (max-width: 768px)
      margin 5px 5px 0 5px
    @media screen and (min-width: 768px)
      margin 10px 10px 0 10px
    @media screen and (min-width: 992px)
      margin 15px 35px 0 35px
    @media screen and (min-width: 1200px)
      width 1200px
      margin 15px auto 0
      margin-bottom 200px
      .layout-left, .layout-right
        padding 0
        @media only screen and (max-width: 768px)
          padding 0
        @media screen and (min-width: 768px)
          padding 0
        @media screen and (min-width: 992px)
          padding 0 10px
        @media screen and (min-width: 1200px)
          padding 0 10px
  .tree
    list-style: none
    position relative
    border 1px solid $color-border
    margin-top 20px
    .h3
      margin-left 0px
      margin-top 10px

  .c-icon
    display inline-block
    height 30px
    width 30px
    left 0px
    background #ffffff
    background-image url("../../assets/menu.png")
    background-size cover
    vertical-align middle

</style>
