<template>
  <div>
  </div>
</template>

<script>
import {getToken, getUserInfo, login, setAccessToken} from '@/utils/oAuth2Login'

export default {
  name: 'OAuth2Login',
  created() {
    const code = this.$route.query.code
    if (code) {
      this.ssologin(code)
    } else {
      login()
    }
  },
  methods: {
    async ssologin(code) {
      try {
        const tokenRes = await getToken(code)
        console.log(tokenRes)
        const userRes = await getUserInfo(tokenRes.data.access_token)
        console.log(userRes)
        setAccessToken(tokenRes.data.access_token)

        this.$router.replace('/')
        alert("登录成功，获取用户："+JSON.stringify(userRes))
      } catch(err) {
        console.log(err)
        alert("登录失败："+err)
      }
    }
  }
}
</script>
