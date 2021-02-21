package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("月资产统计信息")
public class AccountInfoMonthStatDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long zc;

  @ApiModelProperty
  private Long sr;

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

}
