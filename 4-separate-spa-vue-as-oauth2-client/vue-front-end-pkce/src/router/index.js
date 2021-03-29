import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: () => import('@/components/HelloWorld') },
    { path: '/ssologin', component: () => import('@/components/OAuth2Login') }
]

export default new VueRouter({
    routes
})
