package xyz.flysium.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 *
 * @author zeno
 */
@ConfigurationProperties("app")
@Component
public class ApplicationProperties {

  private String product;

  private String version;

  private String company;

  private String toYou;

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getToYou() {
    return toYou;
  }

  public void setToYou(String toYou) {
    this.toYou = toYou;
  }
}
