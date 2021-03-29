<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <router-view/>
        <p>
            <button @click="handleLogin">1.统一认证登录</button>
        </p>
        <p>
            <button @click="getToken">2.查询本地缓存的access_token</button>
        </p>
        <p>
            <button @click="getUserInfo">3.携带access_token查询用户资源接口</button>
        </p>
        <p>
            <button @click="getResource">4.携带access_token调用一个资源服务器的资源接口</button>
        </p>
        <p>
            <button @click="handleLogout">5.退出登录</button>
        </p>
        <p>
            For a guide and recipes on how to configure / customize this project,<br>
            check out the
            <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
        </p>
        <h3>Installed CLI Plugins</h3>
        <ul>
            <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel" target="_blank"
                   rel="noopener">babel</a></li>
            <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint" target="_blank"
                   rel="noopener">eslint</a></li>
        </ul>
        <h3>Essential Links</h3>
        <ul>
            <li><a href="https://vuejs.org" target="_blank" rel="noopener">Core Docs</a></li>
            <li><a href="https://forum.vuejs.org" target="_blank" rel="noopener">Forum</a></li>
            <li><a href="https://chat.vuejs.org" target="_blank" rel="noopener">Community Chat</a></li>
            <li><a href="https://twitter.com/vuejs" target="_blank" rel="noopener">Twitter</a></li>
            <li><a href="https://news.vuejs.org" target="_blank" rel="noopener">News</a></li>
        </ul>
        <h3>Ecosystem</h3>
        <ul>
            <li><a href="https://router.vuejs.org" target="_blank" rel="noopener">vue-router</a></li>
            <li><a href="https://vuex.vuejs.org" target="_blank" rel="noopener">vuex</a></li>
            <li><a href="https://github.com/vuejs/vue-devtools#vue-devtools" target="_blank"
                   rel="noopener">vue-devtools</a></li>
            <li><a href="https://vue-loader.vuejs.org" target="_blank" rel="noopener">vue-loader</a></li>
            <li><a href="https://github.com/vuejs/awesome-vue" target="_blank" rel="noopener">awesome-vue</a></li>
        </ul>
    </div>
</template>

<script>
    import {login,logout, getAccessToken, getUserInfo} from '@/utils/oAuth2Login'
    import axios from 'axios'

    export default {
        name: 'HelloWorld',
        props: {
            msg: String
        },
        methods: {
            handleLogin() {
                login()
            },
            getToken() {
                alert(getAccessToken())
            },
            async getUserInfo() {
                const userRes = await getUserInfo(getAccessToken())
                alert("获取用户："+JSON.stringify(userRes))
            },
            async getResource() {
                const token = getAccessToken();
                const resource = await axios({
                    url: 'http://localhost:8050/resource',
                    headers: {
                        Accept: 'application/json',
                        Authorization: `Bearer ${token}`
                    }
                })
                alert("获取资源服务器的资源："+JSON.stringify(resource))
            },
            handleLogout() {
                logout(function (data) {
                    alert("退出成功")
                })
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
