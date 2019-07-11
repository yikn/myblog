/* eslint-disable */
<template>
  <div class="class">
    <div class="header">
      <img class="img" src="../../assets/menu.png">
      <span class="text"><h2>文章分类</h2></span>
    </div>
    <div class="tree">
      <iv-tree :data="categoryList"></iv-tree>
    </div>
  </div>
</template>
<script>

export default {
  data () {
    return {
      categoryList: []
    }
  },

  created () {
    this.listCategory()
  },

  methods: {
    listCategory () {
      this.$http({
        url: '/api/category'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.categoryList = data.categoryList
        }
      }).then(response => {
        this.$refs.browseMore.stopLoading()
      }).catch(error => {
        this.$refs.browseMore.stopLoading()
        console.log(error)
      })
    }
  }
}

</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/theme.styl";

  .class
    position relative
    border 1px solid $color-border
    padding 20px
    margin-top 20px
    .header
      .img
        display inline-block
        height 30px
        weight 30px
        vertical-align middle
      .text
        display inline-block
        vertical-align middle
    .tree
      margin-top -20px
      padding 20px

</style>
