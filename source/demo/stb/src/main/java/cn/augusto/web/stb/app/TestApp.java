package cn.augusto.web.stb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@SpringBootApplication
//@ComponentScan("cn.augusto.web.stb")
//@Import({cn.augusto.web.stb.controller.LoginController.class, cn.augusto.web.stb.controller.TestController.class})
public class TestApp {
  public static void main(String args[]){
    SpringApplication.run(TestApp.class, args);
  }
}
