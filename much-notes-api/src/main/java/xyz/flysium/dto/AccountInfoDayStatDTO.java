package xyz.flysium.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("天资产统计信息")
public class AccountInfoDayStatDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long zc;

  @ApiModelProperty
  private Long sr;

  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @ApiModelProperty
  private Date time;

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

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

}
