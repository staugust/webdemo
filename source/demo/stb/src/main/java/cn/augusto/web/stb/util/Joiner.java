package cn.augusto.web.stb.util;

import java.util.Collection;

public class Joiner {
  String delimiter;
  static Joiner instance = null;
  public static Joiner on(String str){
    if(instance == null ){
      synchronized(Joiner.class){
        if(instance == null){
          instance = new Joiner();
        }
      }
    }
    instance.delimiter = str;
    return instance;
  }

  public String join(Collection<String> collections){
    StringBuilder sb = new StringBuilder();
    collections.forEach(
        (String ele) -> {
          sb.append(delimiter);
          sb.append(ele);
        }
    );
    sb.delete(0, delimiter.length());
    return sb.toString();
  }
}
