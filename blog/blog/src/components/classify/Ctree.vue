<template>
<div class="ctree">
    <li>
        <div @click='toggle'>
            <div v-if='isFolder' class="fa fa-file" :class="[open?'fa-folder-open':'fa-folder']"><a href="javascript:void(0)">{{model.name}}</a></div>
            <!--isFolder判断是否存在子级改变图标-->
            <div v-else class="fa fa-file-text"><a :href="'/category/'+model.id">{{model.name}}</a></div>
        </div>
        <ul v-show="open">
            <ptree v-for="(item,index) in model.categoryList" :key="index" :model="item"></ptree>
        </ul>
    </li>
</div>
</template>
<script type="text/javascript">
export default {
  name: 'ptree',
  props: ['model'],
  components: {},
  data () {
    return {
      open: false
    }
  },
  computed: {
    isFolder: function () {
      return this.model.pid == 0
    }
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.open = !this.open
      }
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/theme.styl";
  .ctree
    list-style none
    padding-left 20px
  .fa
    margin 10px
  .fa-file-text
    margin-left  10px

</style>
