<template>
  <div class="app-container">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="文章标题" prop="title">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="文章描述" prop="description">
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <el-form-item label="文章分类" prop="categoryid">
          <el-cascader v-model="value" :options="options" @change="handleChange"></el-cascader>
      </el-form-item>
      <el-form-item label="文章内容">
        <mavon-editor ref=md v-model="form.content"  @change="mavonChangeHandle"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import MavonEditor from 'mavon-editor' 
import 'mavon-editor/dist/css/index.css'
import marked from 'marked'
import { connect } from 'net';

export default {
  components:{
      'mavon-editor': MavonEditor.mavonEditor
  },
  data() {
    return {
      selected_id: this.$route.params.id,
      list:[],
      form: {
        title: "",
        description: "",
        category_id: null,
        content:"",
        content_format:""
      },
      value:[],
      options:[],
      rules: {
        title: [
          { required: true, message: "请输入文章标题", trigger: "blur" },
          { min: 3, max: 15, message: "长度在 3 到 8 个字符", trigger: "blur" }
        ],
        region: [{ required: true, message: "请输入简述", trigger: "change" }]
      }
    };
  },
  created(){
    this.initform()
  },
  methods: {
    initform(){
      let id=this.selected_id
      if(this.selected_id==undefined){
      }
      else{
        this.$http({
        url:'/api/article/'+id,
        methods:'get'
      }).then(({data})=>{
          if (data && data.code === 200) {
            this.value=[1,8]
            this.form.title=data.article.title
            this.form.description=data.article.description
            this.form.categoryid=data.article.category_id
            this.form.content=data.article.content
        }
      })
      }

      this.$http({
        url:'/api/casadecategory',
        methods:'get'
      }).then(({data})=>{
          if (data.code === 200) {
            this.options=data.list
        }
      })

    },
    submitForm() {
      let id=this.selected_id
      if(this.selected_id==undefined){
        this.$http({
        url:'/api/addarticle',
        method:'post',
        data: this.$http.adornData(this.form)
      }).then(({data})=>{
          if (data.code === 200) {
            alert("ok")
            window.location.href('/article')
        }
      })
      }
      else{
        this.$http({
        url:'/api/updatearticle/'+id,
        method: 'post',
        data: this.$http.adornData(this.form),
      }).then(({data})=>{
          if (data.code === 200) {
            alert("success")
            window.close()
        }
      })
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleChange(){
      this.form.category_id=this.value[1]
      console.log(this.form.category_id)
    },
    mavonChangeHandle(value,render){
      this.form.content_format=marked(value)
    },
    
  }
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

