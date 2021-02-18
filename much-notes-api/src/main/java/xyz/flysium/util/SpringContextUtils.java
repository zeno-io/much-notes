package xyz.flysium.util;

import java.util.function.Supplier;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import xyz.flysium.support.spring.UtilsSpringApplicationRunListener;

/**
 * Spring 工具类
 *
 * @author zeno
 */
public class SpringContextUtils {

  /**
   * 应用上下文
   */
  private static ApplicationContext context;

  private SpringContextUtils() {
  }

  /**
   * 获取应用上下文
   */
  public static ApplicationContext getApplicationContext() {
    return context;
  }

  /**
   * 设置应用上下文, 通过 {@link UtilsSpringApplicationRunListener} 设置
   */
  public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
    // 避免重复设置
    if (context != null) {
      return;
    }

    MutablePropertySources propertySources = applicationContext.getEnvironment()
      .getPropertySources();
    // org.springframework.cloud.bootstrap.BootstrapApplicationListener#BOOTSTRAP_PROPERTY_SOURCE_NAME
    if (!propertySources.contains("bootstrap")) {
      context = applicationContext;
    }
  }

  /**
   * 根据类型获取 bean 实例
   *
   * @param beanClass bean 类型
   * @param <T>       bean 类型
   * @return Bean 实例
   * @throws BeansException bean 不存在，或者有多个 bean 且优先级相同
   */
  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }

  /**
   * 根据类型获取 bean 实例
   *
   * @param beanName  bean 名称
   * @param beanClass bean 类型
   * @param <T>       bean 类型
   * @return Bean 实例
   * @throws BeansException bean 不存在，或者有多个 bean 且优先级相同
   */
  public static <T> T getBean(String beanName, Class<T> beanClass) {
    return context.getBean(beanName, beanClass);
  }

  /**
   * 根据类型获取 bean 实例，如果不存在 bean, 返回自定义的实例
   *
   * @param beanClass bean 类型
   * @param supplier  不存在 bean 时生成自定义实例的提供者
   * @param <T>       bean 类型
   * @return Bean 实例
   * @throws BeansException bean 不存在，或者有多个 bean 且优先级相同
   */
  public static <T> T getBean(Class<T> beanClass, Supplier<T> supplier) {
    try {
      return context.getBean(beanClass);
    } catch (NoSuchBeanDefinitionException e) {
      return supplier.get();
    }
  }

}
