package com.demo.test.util;
import com.demo.util.log.Log;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 封装driver工具，用来操作浏览器
 */
public class Client {
    public static ArrayList<WebDriver> driverList = new ArrayList();

    /**
     * 打开浏览器客户端，传参为浏览器名称
     * @param driverName
     * @return
     */
    public static WebDriver openClient(String driverName){
        HashMap<String,String> driverinfo = Client.getDriverInfo(driverName);
        System.out.println(driverinfo);
        System.setProperty(
                driverinfo.get("propertyKey"),
                driverinfo.get("driverDirctory")
        );
        WebDriver driver = null;
        try {
            driver = (WebDriver)Class.forName(driverinfo.get("packagePath")).newInstance();
            driver.manage().window().maximize();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * 显示等待封装
     * @param driver
     * @param element
     * @param second
     * @return
     */
    public static WebElement waitWebElement(WebDriver driver,By element,int second){
        WebElement waitElement = null;
        WebDriverWait wait = new WebDriverWait(driver,second,1);
        try{
            waitElement = wait.until(new ExpectedCondition<WebElement>(){
                public WebElement apply(WebDriver driver){
                    return driver.findElement(element);
                }
            });
        }catch(Exception e){
            System.out.println("未找到该元素:"+element.toString());
        };
        return waitElement;
    }

    /**
     * 判断元素是否存在
     * @param driver
     * @param element
     * @return
     */
    public static boolean isWebElementExist(WebDriver driver,By element){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.findElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 关闭窗口
     * @param driver
     */
    public static void close(WebDriver driver){
        driver.close();
    }

    /**
     * 关闭所有窗口，关闭driver进程
     * @param driver
     */
    public static void quit(WebDriver driver){
        driver.quit();
    }

    /**
     * 获取当前窗口alert框
     * @param driver
     */
    public static Alert getAlert(WebDriver driver){
        return driver.switchTo().alert();
    }

    /**
     * 根据driver Name获取对应配置信息
     * @param driverName
     * @return
     */
    private static HashMap<String,String> getDriverInfo(String driverName){
        String info = null;
        try {
            info = JsonPath.read(
                    new File(
                            Client.class.getResource("/config/driver.json").getPath()
                    ),
                    "$.driverinfo.driver[?(@.name == '"+driverName+"')]"
            ).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,String> infoMap = new HashMap();
        infoMap.put("packagePath",jsonArrayToString(info,"$..packagePath"));
        infoMap.put("propertyKey",jsonArrayToString(info,"$..propertyKey"));
        String path = Client.class.getResource("/").getPath();
        infoMap.put(
                "driverDirctory",
                path+jsonArrayToString(info,"$..driverDirctory")
        );
        return infoMap;
    }

    /**
     * 部分情况出现单内容但是为数组类型，通过该方法将数组转为String
     * @return
     */
    private static String jsonArrayToString(String json,String path){
        JSONArray content = JsonPath.parse(json).read(path);
        return content.get(0).toString();
    }

    /**
     * 关闭所有driver
     * @param confirm
     */
    public static void quitAll(boolean confirm){
        try{
            if(confirm){
                for(WebDriver driver : driverList){
                    driver.quit();
                }
            }
        }catch(Exception e){
            Log.error("关闭失败");
        }

    }


}


