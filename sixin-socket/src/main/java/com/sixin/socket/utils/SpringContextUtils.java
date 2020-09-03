package com.sixin.socket.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 获取ApplicationContext和Object的工具类
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void setApplicationCtx(ApplicationContext arg0){
        applicationContext = arg0;
    }
    @Override
    public  void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }
 
    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    /**
     * 根据bean的id来查找对�?
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
     
    /**
     * 根据bean的class来查找对�?
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }
     
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
}