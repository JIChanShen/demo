package com.demo.util.extentx;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Calendar;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String filePath) {
        if (extent == null)
            createInstance(filePath);
        return extent;
    }


    public static void createInstance(String filePath) {
        extent = new ExtentReports();
        extent.setSystemInfo("os", "windows");
        extent.attachReporter(createHtmlReporter(filePath), createExtentXReporter());
    }

    public static ExtentHtmlReporter createHtmlReporter(String filePath){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
        //报表位置
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        //使报表上的图表可见
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(filePath);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("demoProject测试报告");
        return htmlReporter;
    }

    public static ExtentXReporter createExtentXReporter() {
        ExtentXReporter extentx = new ExtentXReporter("127.0.0.1",27017);

        Calendar time = Calendar.getInstance();
        StringBuilder timeBuilder = new StringBuilder(time.get(Calendar.YEAR));
        timeBuilder.append(time.get(Calendar.MONTH)+1);
        timeBuilder.append(time.get(Calendar.DAY_OF_MONTH));
        timeBuilder.append(time.get(Calendar.HOUR_OF_DAY));
        timeBuilder.append(time.get(Calendar.MINUTE));
        extentx.config().setProjectName("demoProject");
        extentx.config().setReportName("Build-"+new String(timeBuilder));
        extentx.config().setServerUrl("http://localhost:1337");

        return extentx;
    }
}
