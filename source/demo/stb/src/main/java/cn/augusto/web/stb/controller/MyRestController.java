package cn.augusto.web.stb.controller;

import cn.augusto.web.stb.entity.user.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

  static class Nidaye{
    private String name;
    private String pwd;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getPwd() {
      return pwd;
    }

    public void setPwd(String pwd) {
      this.pwd = pwd;
    }
  }

  @Autowired
  private UserBean userBean;


  @RequestMapping("/nidaye")
  public Nidaye nidaye(){
    Nidaye nd = new Nidaye();
    nd.setName("daa");
    nd.setPwd("pwd");
    return nd;
  }

  @RequestMapping("/user")
  public UserBean userBean(){
    return getUserBean();
  }

  public UserBean getUserBean() {
    return userBean;
  }

  public void setUserBean(UserBean userBean) {
    this.userBean = userBean;
  }
}
