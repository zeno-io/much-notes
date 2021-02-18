package xyz.flysium.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前端请求
 *
 * @author zeno
 */
@ApiModel("前端请求")
public abstract class FrontEndRequest implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  protected String from;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }
}
