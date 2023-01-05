package net.salatschuessel.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(value = { "net.salatschuessel" })
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class MyConfiguration {
}
