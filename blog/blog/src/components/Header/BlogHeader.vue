<template>
  <div class="simple-header">
    <transition name="slide-fade">
      <div id="header"  v-show="show">
    <router-link id="logo" to="/">
      <img src="..\..\assets\logo.png">
      <span class="motto">see you space cowboy, someday, somewhere</span>
    </router-link>
    <ul id="nav">
      <li>
        <form id="search-form" action="/articles/search">
      <span class="algolia-autocomplete" style="position: relative; display: inline-block; direction: ltr;">
        <input
        type="text" id="search-query-nav" class="search-query st-default-search-input aa-input" name="keywords" v-model="keywords" @keyup.enter="submit"
        autocomplete="off" spellcheck="false" role="combobox" aria-autocomplete="list" aria-expanded="false"
        aria-owns="algolia-autocomplete-listbox-0" dir="auto" style="position: relative; vertical-align: top;">
        <pre
        aria-hidden="true"
        style="position: absolute; visibility: hidden; white-space: pre; font-family: system-ui; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; word-spacing: 0px; letter-spacing: normal; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre>
        <span
        class="aa-dropdown-menu" role="listbox" id="algolia-autocomplete-listbox-0"
        style="position: absolute; top: 100%; z-index: 100; display: none; left: 0px; right: auto;"><div
        class="aa-dataset-1"></div></span></span>
        </form>
      </li>

      <li><a href="/" class="nav-link contribute">首页</a></li>
      <li><a href="/category/2" class="nav-link contribute">分类</a></li>
      <li><a href="/article/2" class="nav-link contribute">关于</a></li>
    </ul>
    </div>
    </transition>
  </div>
</template>

<script>
export default {
  data () {
    return {
      show: true,
      keywords: ''
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "stylus/header.styl";
   // 添加导航栏显示动画
   // 自带动画
  .slide-fade-enter-active ,.slide-fade-leave-active {
    transition: all .8s ease;
  }
  .slide-fade-leave-to ,.slide-fade-enter {
    /* .slide-fade-leave-active for below version 2.1.8 */
    transform: translateY(-70px);
    opacity: 0;
  }
</style>
