<template>
  <div class="setting">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="cantact">
        <el-input v-model="form.contact"></el-input>
      </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="submitForm('form')">修改密码</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>


export default {
  data() {
    return {
      selected_id: this.$route.params.id,
      list:[],
      form: {
        name: "yzj59",
        email: "yeah_59@126.com",
        category_id: null,
        contact:"18687880643",
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

<style lang="stylus" scoped>

    .setting
        margin : 100px 280px 0px 50px
    .line 
        text-align: center
</style>

