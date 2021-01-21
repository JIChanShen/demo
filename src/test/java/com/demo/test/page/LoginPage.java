package com.demo.test.page;

import com.demo.test.util.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private static String url = "http://127.0.0.1/zentao/user-login-L3plbnRhby8=.html";
    private static By loginButton = By.id("submit");
    private static By userInput = By.id("account");
    private static By passInput = By.name("password");

    /**
     * 前往登录页
     * @param driver
     */
    public static void get(WebDriver driver){
        driver.get(url);
        isLoginPage(driver);
    }

    /**
     * 普通登录
     * @param driver
     * @param username
     * @param password
     */
    public static void login(WebDriver driver,String username,String password){
        WebElement submit = Client.waitWebElement(driver,loginButton,10);
        driver.findElement(userInput).sendKeys(username);
        driver.findElement(passInput).sendKeys(password);
        submit.click();
    }

    /**
     * 通过登录按钮是否出现判断是否处于登录页
     * @param driver
     */
    public static void isLoginPage(WebDriver driver){
        Client.waitWebElement(driver,loginButton,10);
    }

    /**
     * 获取是否登录失败
     * @param driver
     */
    public static boolean loginFalseAlertExist(WebDriver driver){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       try{
           String text = Client.getAlert(driver).getText();
           if(text.length()>=0){
               return true;
           }
       } catch(Exception e){
            return false;
       }
       return false;
    }


}
