package cn.augusto.web.stb.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LoginController {
  @Bean
  public ServletRegistrationBean servletRegistrationBean(){
    return new ServletRegistrationBean(new CustomServlet(),"/someOtherUrl/*");
  }
  @RequestMapping("/")
  public String index(){
    return "/index";
  }

  @RequestMapping("/login/")
  public String login(){
    return "/index";
  }

  public static void main(String[] args) {
    SpringApplication.run(LoginController.class, args);
  }
}