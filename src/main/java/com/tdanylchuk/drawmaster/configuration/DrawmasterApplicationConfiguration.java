package com.tdanylchuk.drawmaster.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;

@ComponentScan(basePackages = "com.tdanylchuk.drawmaster")
public class DrawmasterApplicationConfiguration {

    @Bean
    public BufferedReader consoleReader() throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    }

    @Bean
    public BufferedWriter consoleWriter() throws UnsupportedEncodingException {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }


}
