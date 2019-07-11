<template>
  <div class="app-container">
    <el-table
      :data="list"
      element-loading-text="Loading"
      border
      fit 
    >
      <el-table-column align="center" label="id" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="200">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="简述" width="545" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论数" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.comment_num }}</span>
        </template>
      </el-table-column>
            <el-table-column label="阅读数" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.read_num }}</span>
        </template>
      </el-table-column>
      <el-table-column label="置顶" width="110" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.top"
            active-color="#13ce66"
            inactive-color="#66B3FF"
            @change="changeSwitch(scope.row.top)">
          </el-switch>
        </template>
      </el-table-column>
       <el-table-column
       align="center"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="edit(scope.row.id)" type="primary" size="small">编辑</el-button>
        <el-button @click="del(scope.row.id)" type="danger" size="small" >删除</el-button>
      </template>
    </el-table-column>
    </el-table>

    <div class="block">
        <el-pagination
          @current-change="handlePageChange"
          background
          layout="prev, pager, next"
          :current-page="current"
          :total="total"
          :page-size="size">
        </el-pagination>
      </div>
  </div>

</template>

<script>

export default {
  data() {
    return {
      list: [],
      listLoading: true,
      value:true,
      dialogvisible:false,
      total:0,
      current:1,
      size:5
    }
  },
  created(){
    this.getlist()
    this.listLoading=false
  },
  methods:{
    getlist(){
      this.$http({
        url:'/api/articles',
        params:{
          current:this.current,
          size:this.size
        },
        methods: 'get'
      }).then(({data})=>{
          if (data && data.code === 200) {
          this.list = data.page
          this.total = data.total
        }
      })
    },
    changeSwitch(istop){
        console.log(istop)
    },
    edit(id){
      console.log(id)
       window.open("/postarticle/"+id)
    },
    del(id){
       this.$http({
        url:'/api/delarticle/'+id,
        methods: 'delete'
      }).then(({data})=>{
          if (data.code === 200) {
          alert("删除成功")
          window.location.reload()
        }else{
          alert("删除失败")
        }
      })
    },
    handlePageChange(current){
        this.current=current
        console.log(this.current)
        this.getlist()
    }

  }
}
</script>

<style lang="stylus">
  .app-container
      margin : 100px 280px 0px 50px
  .block
      margin-top : 50px
</style>

