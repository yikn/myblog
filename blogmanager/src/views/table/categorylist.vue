<template>
<div class="categorytable">
  <el-table
    :data="tableData"
    style="width: 100%;margin-bottom: 20px;"
    row-key="value"
    border
    default-expand-all
    :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
    <el-table-column
      label="分类id"
      sortable
      width="180">
        <template slot-scope="scope">
          {{ scope.row.value }}
        </template>
    </el-table-column>
    <el-table-column
      prop="label"
      label="分类名称"
      sortable
      width="180">
    </el-table-column>
    <el-table-column>
      <template slot-scope="scope">
        <el-button @click="edit(scope.row.value,scope.row.label)" type="primary" size="small">编辑</el-button>
        <el-button @click="del(scope.row.value)" type="danger" size="small" >删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-button @click="addParentVisible=true" type="primary" size="small">新建父类</el-button>
  <el-button @click="addChildrenVisible=true" type="primary" size="small" >新建子级分类</el-button>

  <el-dialog title="修改分类名称" :visible.sync="editFormVisible" width="30%" :modal-append-to-body='false'>
    <el-form :model="editdata" label-width="80px">
      <el-form-item label="分类id" :label-width="formLabelWidth" >
        <el-input v-model="editdata.id" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="分类名称" :label-width="formLabelWidth" >
        <el-input v-model="editdata.name" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="editFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitEditForm()">确 定</el-button>
    </div>
  </el-dialog>

    <el-dialog title="新建父级分类" :visible.sync="addParentVisible" width="30%" :modal-append-to-body='false'>
    <el-form :model="addParent" label-width="80px">
      <el-form-item label="分类名称" :label-width="formLabelWidth" >
        <el-input v-model="addParent.name" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="addParentVisible = false">取 消</el-button>
      <el-button type="primary" @click="subimitParentForm()">确 定</el-button>
    </div>
  </el-dialog>
  
  <el-dialog title="新建子级分类" :visible.sync="addChildrenVisible" width="30%" :modal-append-to-body='false'>
    <el-form :model="addParent" label-width="80px">
      <el-form-item label="分类名称" :label-width="formLabelWidth" >
        <el-input v-model="addChildren.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item >        
      <el-select v-model="addChildren.pid" placeholder="请选择父分类">
        <el-option
      v-for="pcategory in parentoptions"
      :key="pcategory.id"
      :label="pcategory.name"
      :value="pcategory.id">
     </el-option>
      </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="addParentVisible = false">取 消</el-button>
      <el-button type="primary" @click="subimitChildForm()">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
  export default {
    data() {
      return {
        parentoptions:[],
        tableData: [],
        editFormVisible:false,
        addParentVisible:false,
        addChildrenVisible:false,
        editdata:{
          id:null,
          name:""
        },
        addParent:{
          name:""
        },
        addChildren:{
          name:"",
          pid:null
        }
      }
    },
    created(){
      this.gettableData()
    },
    methods: {
      gettableData(){
        this.$http({
        url:'/api/getparentcategory',
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
          this.parentoptions = data.list
        }
      }),
        this.$http({
        url:'/api/casadecategory',
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
          this.tableData = data.list
        }
      })
      },
      edit(id,name){
        console.log(id)
        console.log(name)
        this.editdata.id=id
        this.editdata.name=name
        this.editFormVisible=true
      },
      del(id){
        console.log(id)
        this.$http({
        url:'/api/deletecategory/'+id,
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
            alert("success")
            window.location.reload()
        }
      })
      },
      submitEditForm(){
        let params ={
            id:this.editdata.id,
            name:this.editdata.name
        }
        this.$http({
        url:'/api/updatecategory',
        params:params,
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
            alert("success")
            window.location.reload()
        }
      })
      },
      subimitParentForm(){
        this.$http({
        url:'/api/addcategory',
        params:{
          pid:0,
          name:this.addParent.name
        },
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
            alert("success")
            window.location.reload()
        }
      })
      },
      subimitChildForm(){
        this.$http({
        url:'/api/addcategory',
        params:{
          pid:this.addChildren.pid,
          name:this.addChildren.name
        },
        method: 'get'
      }).then(({data})=>{
          if (data.code === 200) {
            alert("success")
            window.location.reload()
        }
      })
      }
    },
  }
</script>

<style lang="stylus" scoped>
  .categorytable
    margin : 50px 1000px 0px 50px
</style>
