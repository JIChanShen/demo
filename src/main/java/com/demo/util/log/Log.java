package com.demo.util.log;

import org.apache.log4j.Logger;

import java.util.HashMap;

public class Log {
    public static HashMap<String, Logger> loggers = new HashMap();

    /**
     * 封装不带tag的debug等级日志
     * @param message
     */
    public static void debug(String message){
        Logger log = getLogger(getClassName());
        log.debug(message);
    }

    /**
     * 封装不带tag的info等级日志
     * @param message
     */
    public static void info(String message){
        Logger log = getLogger(getClassName());
        log.info(message);
    }

    /**
     * 封装不带tag的warn等级日志
     * @param message
     */
    public static void warn(String message){
        Logger log = getLogger(getClassName());
        log.warn(message);
    }

    /**
     * 封装不带tag的error等级日志
     * @param message
     */
    public static void error(String message){
        Logger log = getLogger(getClassName());
        log.debug(message);
    }

    /**
     * 封装不带tag的fatal等级日志
     * @param message
     */
    public static void fatal(String message){
        Logger log = getLogger(getClassName());
        log.fatal(message);
    }

    /**
     * 返回最近的调用目标
     * @return
     */
    private static String getClassName(){
        Throwable th = new Throwable();
        StackTraceElement[] se = th.getStackTrace();
        StackTraceElement el = se[2];
        return el.getClassName();
    }

    /**
     * 获取调用对象的logger对象
     * @param className
     * @return
     */
    private static Logger getLogger(String className){
        if(loggers.containsKey(className)){
            return loggers.get(className);
        }
        try {
            loggers.put(className,Logger.getLogger(Class.forName(className)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loggers.get(className);
    }
}
