package cn.augusto.web.stb.yaml;

import cn.augusto.web.stb.util.Joiner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class YamlLister {

  @PostConstruct
  public void init() throws IOException {
    setMdPath(Paths.get(mdConfPath));
    walk(getMdPath(), "");
  }

  public boolean isIndex(String url) {
    return Files.isDirectory(getMdPath().resolve(url));
  }

  public boolean isPage(String url) {
    return Files.isRegularFile(getMdPath().resolve(url));
  }

  public List<String> getItems(String url) throws IOException {

    List<String> lst = new ArrayList<>();
    Path dir = getMdPath().resolve(url);
    Files.list(dir).forEach(
        path -> {
          System.out.println(getMdPath().relativize(path).toString());
          lst.add(path.getFileName().toString());
        }
    );
    return lst;
  }

  public String getContent(String url) {
    try{
      Path filePath = getMdPath().resolve(url);
      InputStream is = new FileInputStream(filePath.toFile());
      BufferedReader bufferedReader = new BufferedReader(
          new InputStreamReader(is, Charset.forName("UTF-8")));
      List<String> lines = bufferedReader.lines().collect(Collectors.toList());
      String content = Joiner.on("\n").join(lines);
      return MarkdownWrapper.parse(content);
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return "";
  }

  private void walk(Path rpath, String root) throws IOException {

  }

  private HashMap<Path, List<String>> indice = new HashMap<>();

  @Value("${augusto.markdown.root}")
  private String mdConfPath;

  public Path getMdPath() {
    return mdPath;
  }

  public void setMdPath(Path mdPath) {
    this.mdPath = mdPath;
  }

  private Path mdPath;
}
