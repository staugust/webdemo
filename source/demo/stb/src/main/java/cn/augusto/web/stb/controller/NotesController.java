package cn.augusto.web.stb.controller;

import cn.augusto.web.stb.util.FileUtil;
import cn.augusto.web.stb.yaml.MarkdownEntity;
import cn.augusto.web.stb.yaml.MarkdownWrapper;
import cn.augusto.web.stb.yaml.YamlLister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class NotesController {
//  @Bean
//  public BeanNameViewResolver beanNameViewResolver(){
//    BeanNameViewResolver resolver = new BeanNameViewResolver();
//    System.out.printf("beanNameViewResolver order -> %d\n", resolver.getOrder());
//    resolver.setOrder(0);
//    return resolver;
//  }
//  @Autowired
//  private BeanNameViewResolver beanNameViewResolver;

  @Autowired
  YamlLister yamlLister;

  @RequestMapping(method = RequestMethod.GET, path = {"/notes/**"}) //,"/notes/{page_id}"})
  public String notes(HttpServletRequest request, Model model ){
    String page_id = new AntPathMatcher()
                         .extractPathWithinPattern( "/{id}/**", request.getRequestURI() );
    if(yamlLister.isPage(page_id)){
      model.addAttribute("page_id", page_id);
      model.addAttribute("yamlContent",
          yamlLister.getContent(page_id));
      return "yaml";
    }
    if(yamlLister.isIndex(page_id)){
      model.addAttribute("page_id", page_id);
      try {
        model.addAttribute("lists", yamlLister.getItems(page_id));
      }catch (Exception e){
        e.printStackTrace();
      }
      return "yml/dir";
    }
    return "error";
  }
}
