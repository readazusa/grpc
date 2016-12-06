package club.lovety.spring.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */

public class RpcLintener implements ServletContextListener {

    private static  final Logger log = LogManager.getLogger(RpcLintener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        applicationContext.getBean("userServiceImpl");
        log.debug("================");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
