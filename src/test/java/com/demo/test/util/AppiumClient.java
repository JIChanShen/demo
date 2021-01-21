package com.demo.test.util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumClient {
    public static void main(String[] args){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","192.168.71.102:5555");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage", "com.cntaiping.tpapptest");
        capabilities.setCapability("appActivity", "com.basicapp.ui.SplashActivity");
        try {
            AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
