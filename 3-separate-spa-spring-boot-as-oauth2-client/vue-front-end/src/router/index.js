import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: () => import('@/components/HelloWorld') }
]

export default new VueRouter({
    routes
})
