package com.github.tutorial.Web.Util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized()");

        WebApplicationContext rootContext =
                WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        System.out.println("Is rootContext null:" + (rootContext == null));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed()");
    }

}
