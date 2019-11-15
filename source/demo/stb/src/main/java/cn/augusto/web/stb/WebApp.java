package cn.augusto.web.stb;

import cn.augusto.web.stb.entity.user.UserBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.Locale;


//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class WebApp {



  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(WebApp.class, args);
    UserBean usb = context.getBean(cn.augusto.web.stb.entity.user.UserBean.class);
    System.out.println(usb.getName());
    System.out.println(usb.getPassword());
  }
}