package com.demo.test.page;

import com.demo.test.util.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage {
    private static By userNav = By.id("userNav");

    /**
     * 根据userNav元素是否存在，来判断是否处于index页面
     * @param driver
     * @param username
     * @return
     */
    public static boolean isIndex(WebDriver driver,String username){
        String text = Client.waitWebElement(driver,userNav,10).getText();
        if(text.equals(username)){
            return true;
        }
        return false;
    }
}
