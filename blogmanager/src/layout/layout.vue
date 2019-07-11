<template>
    <div class="app-wrapper">
        <sidebar class="sidebar-container"></sidebar>
        <div class="main-container">
            <navbar></navbar>
            <appmain></appmain>
        </div>
    </div>
</template>

<script>

import sidebar from '@/views/sidebar.vue'
import appmain from '@/views/appmain.vue'
import navbar from '@/views/navbar.vue'

export default {
    name: 'layout',
    components:{
        sidebar,
        appmain,
        navbar
    }    
}
</script>

<style lang="stylus">
    .app-wrapper
        height 100%
        width 100%
        .sidebar-container
            display inline-block
            width 13%
            position fixed
            left 0
            top 0
            bottom 0
            z-index 1001
        .main-container
            display inline-block
            width 87%
            position fixed
            right 0
            top 0
            bottom 0 
            overflow:scroll
            .appcontainer
                margin 50 50 50 50

</style>
