<template>
  <div class="about">
    <img class="background" src="../../assets/background.jpg" alt="">
    <img class="avatar" src="../../assets/avatar.png" alt="">
    <p class="name">Yzj</p>
    <p class="desc">缺乏想象力的狭隘、苛刻、自以为是的命题、空洞的术语、被篡夺的理想、僵化的思想体系——对我来说，真正可怕的是这些东西</p>
    <ul class="social">
      <li><a href="https://github.com/lockshe" target="_blank"><img src="../../assets/icon/Github.png" alt=""></a></li>
      <li><a href="https://www.zhihu.com/people/yang-zi-ji-12" target="_blank"><img src="../../assets/icon/zhihu.png" alt=""></a></li>
    </ul>
  </div>
</template>

<script type="text/ecmascript-6">
export default {

}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/theme.styl";

  .about
    position relative
    text-align center
    border 1px solid $color-border
    padding-bottom 20px
    margin-top 90px
    img.background
      position absolute
      top 0
      left 0
      right 0
      width 100%
      height 125px
      z-index 1
    img.avatar
      position relative
      margin 75px auto 15px
      width 100px
      height 100px
      border 5px solid $color-gradually-gray-91
      border-radius 50%
      z-index 9
    .name
      font-size 22px
      color $color-typegraphy-title
      line-height 30px
      font-weight 700
    .desc
      font-size 15px
      color $color-secondary-info
      line-height 30px
      font-weight 100
    .social
      list-style-type none
      text-align center
      padding 0 20px
      margin-top 15px
      > li
        display: inline-block;
        padding 8px
        a
          display block
          width: 44px
          height: 44px
          margin: auto
          img
            width 100%
    .line
      height 1px
      background-color $color-gradually-gray-91
      margin 10px 20px
    h4
      font-size 19px
      margin 30px 0 20px
      font-weight 600
    .progresses
      padding 0 20px
      p.title
        height 38px
        line-height 38px
        text-align right
      .bar
        margin 10px 0
</style>
