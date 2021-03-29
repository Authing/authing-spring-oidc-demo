import axios from 'axios'
import Cookies from 'js-cookie'
import qs from 'qs'

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
 * 使用OAuth2的密码模式登录
 */
export function login(username, password) {
    const param = qs.stringify({
        client_id: process.env.VUE_APP_clientId,
        client_secret: process.env.VUE_APP_clientSecret,
        username: username,
        password: password,
        grant_type: process.env.VUE_APP_grantType
    })

    axios.post(`${process.env.VUE_APP_accessTokenUri}?${param}`, null)
        .then((data ) => {
            if (data.data.access_token) {
                setAccessToken(data.data.access_token)
                alert("登录成功,获取到access_token："+JSON.stringify(data.data))
            } else {
                removeAccessToken()
                alert("退出失败："+JSON.stringify(data))
            }
         })
}

/**
 * 根据token获取统一认证用户信息
 * @param  {String} token
 * @return {Promise Object}
 */
export function getUserInfo() {
    const token = getAccessToken()
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
export function logout(callBack) {
    // 1. 调用认证中心退出接口
    return axios.get(`${process.env.VUE_APP_logoutUri}`, {
        headers: {'withCredentials': 'true'}
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
