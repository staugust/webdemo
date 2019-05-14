package cn.augusto.web.stb.yaml;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MarkdownEntity {

  public static String TAG_WIDTH = "<style type=\"text/css\"> %s { width:85%%} </style>";

  public String getCss() {
    return css;
  }

  public void setCss(String css) {
    this.css = css;
  }

  // css 样式
  private String css;

  // 最外网的div标签， 可以用来设置样式，宽高，字体等
  private Map<String, String> divStyle = new ConcurrentHashMap<>();

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  // 转换后的html文档
  private String html;

  public MarkdownEntity() {
  }

  public MarkdownEntity(String html) {
    this.html = html;
  }

  @Override
  public String toString() {
    return css + "\n<div " + parseDiv() + ">\n" + html + "\n</div>";
  }


  private String parseDiv() {
    StringBuilder builder = new StringBuilder();
    for (Map.Entry<String, String> entry : divStyle.entrySet()) {
      builder.append(entry.getKey()).append("=\"")
          .append(entry.getValue()).append("\" ");
    }
    return builder.toString();
  }


  public void addDivStyle(String attrKey, String value) {
    if (divStyle.containsKey(attrKey)) {
      divStyle.put(attrKey, divStyle.get(attrKey) + " " + value);
    } else {
      divStyle.put(attrKey, value);
    }
  }


  public void addWidthCss(String tag) {
    String wcss = String.format(TAG_WIDTH, tag);
    css += wcss;
  }
}
