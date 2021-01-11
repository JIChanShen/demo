package com.demo.test.testcase;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Case {
    @Test
    public void getToken(){
        String result = RestAssured.given()
                .param("corpid","ww04c2a06e22bcc5a7")
                .param("corpsecret","4M8-QGRUzKDTdh0SWLGvUb_RqF1KWu-rgHB8PsF9UZ4")
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .body("errcode",equalTo(0))
                .extract()
                .path("access_token");
        System.out.println(result);
    }

    public static void main(String[] args){
        System.out.println(Case.class.getResource("").getPath());
    }
}
