package com.demo.test.service;

import com.jayway.jsonpath.JsonPath;

/**
 * 封装getToken接口
 */
public class GetToken {
    public static String getToken(){
        String interfaceInfo = JsonPath.parse(
                GetToken.class.getResource("/interfacedata/getToken.json").getPath()
        ).read("$..interfaceInfo");

        System.out.println(interfaceInfo);
//
//        String result = RestAssured.given()
//                .param("corpid","ww04c2a06e22bcc5a7")
//                .param("corpsecret","4M8-QGRUzKDTdh0SWLGvUb_RqF1KWu-rgHB8PsF9UZ4")
//                .when()
//                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
//                .then()
//                .body("errcode",equalTo(0))
//                .extract()
//                .path("access_token");
//        System.out.println(result);

        return "1234";
    }

    public static void main(String[] args){
        GetToken.getToken();
    }
}
