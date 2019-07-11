<template>
  <div class="article-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <section-title :mainTitle="pcategoryname" :subTitle="categoryname" :tipText="'View More'" :tipHref="'/'">
          </section-title>
          <article-list-cell v-for="article in articleList" :article="article" :key="article.id"></article-list-cell>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
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
import ArticleListHeader from '@/components/views/ArticleListHeader'
import ArticlePageContent from '@/components/views/ArticlePageContent'
import ArticlePageFooter from '@/components/views/ArticlePageFooter'
import ArticleListCell from '@/components/views/ArticleListCell'
import SectionTitle from '@/components/section/SectionTitle'
import Ctree from '@/components/classify/Ctree'
import merge from 'lodash/merge'
import {treeDataTranslate} from '@/utils'
import {DefaultLimitSize} from '@/common/js/const'
import SectionTitleVue from '../section/SectionTitle.vue'

export default {
  data () {
    return {
      pcategoryname: '',
      categoryname: '',
      articleList: [],
      categoryList: [],
      selected_category: this.$route.params.id,
      currentPage: 1,
      pageSize: DefaultLimitSize,
      categoryId: this.$route.params.categoryId,
      menuParams: {},
      noMoreData: false,
      current:1,
      size:5
    }
  },
  created () {
    this.listArticle()
    this.listCategory()
  },
  methods: {
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
          if (data.page.totalPage <= data.page.currPage) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.articleList = data.page
        }
      })
    },
    listCategory () {
      let params = {}
      params.type = 0
      this.$http({
        url: '/api/category',
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = data.categoryList
          for (var i = 0; i < this.categoryList.length; i++) {
            var item = this.categoryList[i]
            for (var j = 0; j < item.categoryList.length; j++) {
              var k = item.categoryList[j]
              if (k.id == this.categoryId) {
                this.categoryname = k.name
                this.pcategoryname = item.name
              }
            }
          }
        }
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
    'article-list-header': ArticleListHeader,
    'article-page-content': ArticlePageContent,
    'article-page-footer': ArticlePageFooter,
    'article-list-cell': ArticleListCell,
    'ptree': Ctree,
    'section-title': SectionTitle
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
@import "../../common/stylus/theme.styl";
  .article-list-content
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
      padding-bottom 20px
      margin-top 90px

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
