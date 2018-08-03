package com.zh.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web应用启动和停止监听器
 * @Author zh2683
 */
public class MyContextListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(MyContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(Thread.currentThread().getName());
        ServletContext servletContext = sce.getServletContext();
        logger.info("contextConfigLocation: " + servletContext.getInitParameter("contextConfigLocation"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("应用关闭: " + Thread.currentThread().getName());
    }
}
