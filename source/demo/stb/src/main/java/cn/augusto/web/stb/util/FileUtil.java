package cn.augusto.web.stb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class FileUtil {
  public static String readAll(String path) throws IOException {
    InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(path);
    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(inputStream, "UTF-8"));
    StringJoiner joiner = new StringJoiner("\n");
    bufferedReader.lines().forEachOrdered(joiner::add);
    return joiner.toString();
  }

  public static InputStream getStreamByFileName(String path) {
    return FileUtil.class.getClassLoader().getResourceAsStream(path);
  }
}


