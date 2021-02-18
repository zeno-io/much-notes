package xyz.flysium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import xyz.flysium.support.cache.EhcacheSupport;

/**
 * Boot.
 *
 * @author zeno
 */
@SpringBootApplication
@MapperScan("xyz.flysium.dao.repository")
public class MuchNotesApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MuchNotesApplication.class).run(args);
    Runtime.getRuntime().addShutdownHook(new Thread(EhcacheSupport.getCacheManager()::close));
  }

}
