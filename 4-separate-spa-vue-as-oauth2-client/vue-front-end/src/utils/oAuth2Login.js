import qs from 'qs'
import {Base64} from 'js-base64'
import axios from 'axios'
import Cookies from 'js-cookie'

const AccessTokenKey = 'access_token'

export function getAccessToken() {
    return Cookies.get(AccessTokenKey)
}

export function setAccessToken(token) {
    return Cookies.set(AccessTokenKey, token)
}

export function removeAccessToken() {
    return Cookies.remove(AccessTokenKey)
}

/**
 * 跳转到统一认证服务地址获取授权码
 * hack方法，接受参数为登录成功后重定向地址，缺省时重定向首页
 */
export function login() {
    const param = qs.stringify({
        client_id: process.env.VUE_APP_clientId,
        response_type: process.env.VUE_APP_responseType,
        scope: process.env.VUE_APP_scope,
        state: process.env.VUE_APP_state,
        redirect_uri: process.env.VUE_APP_redirectUri
    })
    window.location.href = `${process.env.VUE_APP_userAuthorizationUri}?${param}`
}

/**
 * 通过code获取token
 * @param  {String} code 授权码
 * @return {Promise Object}
 */
export function getToken(code) {
    //有两种方式，使用basic或者form,任选其一,推荐使用basic

    //1 使用form
    // const param = qs.stringify({
    //     client_id: process.env.VUE_APP_clientId,
    //     client_secret: process.env.VUE_APP_clientSecret,
    //     code,
    //     grant_type: process.env.VUE_APP_grantType,
    //     redirect_uri: process.env.VUE_APP_redirectUri
    // })
    // // redirect_uri不放在qs中序列化，转码导致请求400，所以放在下面拼接
    // return axios.post(`${process.env.VUE_APP_accessTokenUri}?${param}`, null, {
    //     headers: {
    //         Accept: 'application/json'
    //     }
    // })

    // 2 使用basic
    const oauthKey = Base64.encode(`${process.env.VUE_APP_clientId}:${process.env.VUE_APP_clientSecret}`)
    const param = qs.stringify({
        code,
        grant_type: process.env.VUE_APP_grantType,
        redirect_uri: process.env.VUE_APP_redirectUri
    })
    // redirect_uri不放在qs中序列化，转码导致请求400，所以放在下面拼接
    return axios.post(`${process.env.VUE_APP_accessTokenUri}?${param}`, null, {
        headers: {
            Accept: 'application/json',
            Authorization: 'Basic ' + oauthKey
        }
    })
}

/**
 * 根据token获取统一认证用户信息
 * @param  {String} token
 * @return {Promise Object}
 */
export function getUserInfo(token) {
    return axios({
        url: `${process.env.VUE_APP_userInfoUri}`,
        headers: {
            Accept: 'application/json',
            Authorization: `Bearer ${token}`
        }
    })
}

/**
 * 注销
 * @return {Promise Object}
 */
export function logout(token, callBack) {
    // 1. 调用认证中心退出接口
    return axios.get(`${process.env.VUE_APP_logoutUri}`, {
        withCredentials: true, //正确写法
        headers: {
            Authorization: `Bearer ${token}`
        }
    }).then((data ) => {
        if (data.data.success) {
            // 2. 删除access_token
            removeAccessToken()
            callBack(data)
        } else {
            alert("退出失败："+JSON.stringify(data))
        }
    })
}
