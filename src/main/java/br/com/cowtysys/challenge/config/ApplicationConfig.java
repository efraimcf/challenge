package br.com.cowtysys.challenge.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@Configuration
@EnableAsync 
@EnableAutoConfiguration
@EnableScheduling
@ImportResource({"classpath:spring/application-config.xml"})
public class ApplicationConfig {

}