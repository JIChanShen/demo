package com.demo.test.testcase;

import com.demo.test.page.IndexPage;
import com.demo.test.page.LoginPage;
import com.demo.test.util.driver.Client;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SelCase {
    WebDriver driver = Client.openClient("chrome");

    @Test
    public void loginSuccess(){
        LoginPage.get(driver);
        LoginPage.login(driver,"admin","Useradmin");
        assertEquals(true, IndexPage.isIndex(driver,"admin"));
    }

    @Test
    public void loginFalse(){
        WebDriver driver = Client.openClient("chrome");
        LoginPage.get(driver);
        LoginPage.login(driver,"admin","Useradmin2");
        boolean result = LoginPage.loginFalseAlertExist(driver);
        assertEquals(true,result);
    }



}
