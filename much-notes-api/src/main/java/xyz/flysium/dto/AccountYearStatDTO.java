package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("年资产统计信息")
public class AccountYearStatDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long zc;

  @ApiModelProperty
  private Long sr;

  @ApiModelProperty
  private Integer month;

  public Long getZc() {
    return zc;
  }

  public void setZc(Long zc) {
    this.zc = zc;
  }

  public Long getSr() {
    return sr;
  }

  public void setSr(Long sr) {
    this.sr = sr;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }
}
