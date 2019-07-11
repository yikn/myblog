<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>

export default {

  data () {
    return {
      msg: 'this is a test'
    }
  },
  created () {
    this.test()
  },
  method: {
    test () {
      this.$http({
        url: '/api/test',
        method: get
      }).then(({data}) => {
        console.log(data)
      })
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
