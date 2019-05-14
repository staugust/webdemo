package cn.augusto.web.stb.config;

import cn.augusto.web.stb.controller.NoteServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServlet;

@EnableWebMvc
@Configuration
@ComponentScan("cn.augusto.web.stb")
public class WebConfig implements WebMvcConfigurer {
  /*
  @Bean
  public ServletRegistrationBean<HttpServlet> stateServlet() {
    ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
    servRegBean.setServlet(new NoteServlet());
    servRegBean.addUrlMappings("/notes/*");
    servRegBean.setLoadOnStartup(1);
    return servRegBean;
  }
  */
}
