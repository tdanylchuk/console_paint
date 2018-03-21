package com.tdanylchuk.drawmaster;

import com.tdanylchuk.drawmaster.configuration.DrawmasterApplicationConfiguration;
import com.tdanylchuk.drawmaster.engine.DrawmasterEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DrawmasterApplication {

    public static void main(String[] args) {
        System.setProperty("java.util.logging.config.file", "logging.properties");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DrawmasterApplicationConfiguration.class);
        DrawmasterEngine drawmasterEngine = applicationContext.getBean(DrawmasterEngine.class);
        drawmasterEngine.start();
    }
}
