package xyz.flysium.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

/**
 * 数据文件解析辅助类
 *
 * @author zeno
 */
public final class DataFileUtils {

  private DataFileUtils() {
  }

  /**
   * 加载文件内容为字符串
   *
   * @param path 文件名称，包含路径
   * @return 文件内容
   */
  public static String loadAsString(String path) {
    ClassPathResource resource = new ClassPathResource(path);
    if (!resource.exists()) {
      throw new IllegalStateException(path + " does not exists");
    }

    try (InputStream inputStream = resource.getInputStream();) {
      return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to read data from file: " + path, e);
    }
  }

}
