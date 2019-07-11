// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'iview/dist/styles/iview.css'
import httpRequest from '@/utils/httpRequest'

// iView UI 组件引入
import {
  Row,
  Col,
  Button,
  Input,
  Progress,
  Tag,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Menu,
  Submenu,
  MenuItem,
  Icon,
  LoadingBar,
  Affix,
  Select,
  Option,
  Carousel,
  CarouselItem,
  DatePicker,
  DatePickerCell,
  Spin,
  Rate,
  Tree,
  Page
} from 'iview'

Vue.component('iv-row', Row)
Vue.component('iv-col', Col)
Vue.component('iv-button', Button)
Vue.component('iv-input', Input)
Vue.component('iv-progress', Progress)
Vue.component('iv-tag', Tag)
Vue.component('iv-dropdown', Dropdown)
Vue.component('iv-dropdown-menu', DropdownMenu)
Vue.component('iv-dropdown-item', DropdownItem)
Vue.component('iv-menu', Menu)
Vue.component('iv-submenu', Submenu)
Vue.component('iv-menu-item', MenuItem)
Vue.component('iv-icon', Icon)
Vue.component('iv-loadingBar', LoadingBar)
Vue.component('iv-affix', Affix)
Vue.component('iv-select', Select)
Vue.component('iv-option', Option)
Vue.component('iv-carousel', Carousel)
Vue.component('iv-carousel-item', CarouselItem)
Vue.component('iv-date-picker', DatePicker)
Vue.component('iv-date-picker-cell', DatePickerCell)
Vue.component('iv-spin', Spin)
Vue.component('iv-rate', Rate)
Vue.component('iv-tree', Tree)
Vue.component('iv-page', Page)

Vue.prototype.$http = httpRequest // Ajax 请求方法
Vue.prototype.bus = new Vue()
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
