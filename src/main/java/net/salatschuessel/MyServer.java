package net.salatschuessel;

import net.salatschuessel.config.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MyConfiguration.class)
public class MyServer {
  public static void main(final String[] args) {
    SpringApplication.run(MyServer.class, args);
  }
}