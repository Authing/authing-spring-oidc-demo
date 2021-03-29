package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//运行前要保证认证服务器已经启动，resource server 1启动
public class AuthorizationCodeLiveTest {

    @Test
    public void givenUser_whenUseFooClient_thenOkForFooResourceOnly() {
//用户john在oauthserver的定义：
// .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()

//fooClientIdPassword在oauthserver的定义：
//        .withClient("fooClientIdPassword")
//                .secret("secret")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .scopes("foo", "read", "write")
//                .accessTokenValiditySeconds(3600) // 1 hour
//                .refreshTokenValiditySeconds(2592000) // 30 days

        final String accessToken = obtainAccessTokenWithAuthorizationCode("tdfOauth2SSO213", "admin", "123456");

        final Response fooResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8080/user/me");
        assertEquals(200, fooResponse.getStatusCode());
        assertNotNull(fooResponse.jsonPath().get("name"));

//        final Response barResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get(RESOURCE_SERVER + "/bars/1");
//        assertEquals(403, barResponse.getStatusCode());
    }

    private String obtainAccessTokenWithAuthorizationCode(String clientId, String username, String password) {
        final String redirectUrl = "xxx";
        final String authorizeUrl = "http://192.168.99.77:9998/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUrl;
        final String tokenUrl = "http://192.168.99.77:9998/oauth/token";

        // user login
        Response response = RestAssured.given().get("http://192.168.99.77:9998/login");
        System.out.println(response.getBody().prettyPrint());
        final String xsrfcookieValue = response.getCookie("XSRF-TOKEN");
        response = RestAssured.given()
//                .auth().form(username,password,springSecurity().withCsrfFieldName("_csrf"))
                .formParams("username", username
                        , "password", password
                        , "_csrf", xsrfcookieValue)
                .post("http://192.168.99.77:9998/login");
        final String cookieValue = response.getCookie("JSESSIONID");

        // get authorization code
        System.out.println("get authorization code " + RestAssured.given()
                .cookie("JSESSIONID", cookieValue).get(authorizeUrl).asString());
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_oauth_approval", "true");
        params.put("authorize", "Authorize");
        params.put("scope.app", "true");
        params.put("scope.foo", "true");
        response = RestAssured.given().cookie("JSESSIONID", cookieValue)
                .formParams(params).post(authorizeUrl);

        assertEquals(HttpStatus.FOUND.value(), response.getStatusCode());

        final String location = response.getHeader(HttpHeaders.LOCATION);
        final String code = location.substring(location.indexOf("code=") + 5);

        // get access token
        params = new HashMap<String, String>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("redirect_uri", redirectUrl);

        response = RestAssured.given().auth().basic(clientId, "secret").formParams(params).post(tokenUrl);
        return response.jsonPath().getString("access_token");
    }

}
