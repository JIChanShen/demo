package com.demo.test.testcase;

import com.demo.util.log.Log;
import org.testng.annotations.Test;

public class LoggerTest {
    @Test
    public void info(){
        Log.info("click");
    }
}
