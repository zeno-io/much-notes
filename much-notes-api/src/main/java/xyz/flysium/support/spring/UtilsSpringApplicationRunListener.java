package xyz.flysium.support.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import xyz.flysium.util.SpringContextUtils;

/**
 * Spring 应用监听器
 *
 * <p>用于设置 {@link xyz.flysium.util.SpringContextUtils#setApplicationContext}</p>
 *
 * @author zeno
 */
public class UtilsSpringApplicationRunListener implements SpringApplicationRunListener {

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public UtilsSpringApplicationRunListener(SpringApplication application, String[] args) {
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    SpringContextUtils.setApplicationContext(context);
  }

}
