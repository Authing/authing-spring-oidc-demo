import qs from 'qs'
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
    // codeVerifier缓存再cookie中
    const codeVerifier = generateRandomString(32)
    Cookies.set('codeVerifier', codeVerifier)
    generateCodeChallenge(codeVerifier).then((codeChallenge) => {
        const param = qs.stringify({
            client_id: process.env.VUE_APP_clientId,
            response_type: process.env.VUE_APP_responseType,
            scope: process.env.VUE_APP_scope,
            state: process.env.VUE_APP_state,
            redirect_uri: process.env.VUE_APP_redirectUri,
            // code_challenge_method: 'PLAIN',  // 使用plain方法
            // code_challenge: codeVerifier
            code_challenge_method: 'S256',   //使用S256方法
            code_challenge: codeChallenge
        })

        window.location.href = `${process.env.VUE_APP_userAuthorizationUri}?${param}`
    })
}


/**
 * 通过code获取token
 * @param  {String} code 授权码
 * @return {Promise Object}
 */
export function getToken(code) {
    const param = qs.stringify({
        client_id: process.env.VUE_APP_clientId,
        code,
        grant_type: process.env.VUE_APP_grantType,
        redirect_uri: process.env.VUE_APP_redirectUri,
        code_verifier:  Cookies.get('codeVerifier')
    })

    console.log(param)
    // redirect_uri不放在qs中序列化，转码导致请求400，所以放在下面拼接
    return axios.post(`${process.env.VUE_APP_accessTokenUri}?${param}`, null, {
        headers: {
            Accept: 'application/json'
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
export function logout(callBack) {
    // 1. 调用认证中心退出接口
    return axios.get(`${process.env.VUE_APP_logoutUri}`, {
        withCredentials: true //正确写法
        // headers: {'withCredentials': 'true'} //错误写法
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


//=========================PKCE相关随机数生成、加密解密等工具类=========================================//
const generateRandomString =(length) => {
    var randomByteArray = new Uint8Array(length);
    window.crypto.getRandomValues(randomByteArray);
    var randomString = btoa(String.fromCharCode.apply(null, randomByteArray));
    var code = randomString.replace(/\+/g, '-')
        .replace(/\//g, '_')
        .replace(/=/g, '');
    return code;
}

const generateCodeChallenge = async (codeVerifier) => {
    const strBuffer = new TextEncoder('utf-8').encode(codeVerifier);
    const hashBuffer = await window.crypto.subtle.digest('SHA-256', strBuffer);
    const byteArray = Array.from(new Uint8Array(hashBuffer));
    var base64String = btoa(String.fromCharCode.apply(null, byteArray));
    var code = base64String.replace(/\+/g, '-')
        .replace(/\//g, '_')
        .replace(/=/g, '');
    return code;
}
//=========================End Of PKCE相关随机数生成、加密解密等工具类=========================================//