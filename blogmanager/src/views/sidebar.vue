<template>
<el-row class="tac">
  <el-col :span="12">
    <el-menu
      default-active="2"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b" router="isrouter">
      <el-submenu index="articles">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>文章管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="article">文章列表</el-menu-item>
          <el-menu-item index="postarticle">发布文章</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="categories">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>分类管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="category">分类列表</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
        <el-menu-item index="setting">
        <i class="el-icon-setting"></i>
        <span >个人设置</span>
      </el-menu-item>
      <el-menu-item>
        <i class="el-icon-location"></i>
        <a target="_blank" href="http://localhost:8002/">博客链接</a>
      </el-menu-item>
    </el-menu>
  </el-col>
</el-row>
</template>


<style lang="stylus" scoped>

    .el-menu-vertical
        width 13%
        position fixed
        top: 0
        left: 0
        bottom : 0
    a
      text-decoration :none
      color:#ffffff
</style>

<script>
export default {
  data(){
    return{
      isrouter:true
    }
  },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>
