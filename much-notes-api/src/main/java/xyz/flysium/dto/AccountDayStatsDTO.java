package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("天账本统计信息")
public class AccountDayStatsDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long total;

  @ApiModelProperty
  private List<UserAccountRecordDTO> data;

  @ApiModelProperty
  private List<AccountInfoDayStatDTO> days;

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<UserAccountRecordDTO> getData() {
    return data;
  }

  public void setData(List<UserAccountRecordDTO> data) {
    this.data = data;
  }

  public List<AccountInfoDayStatDTO> getDays() {
    return days;
  }

  public void setDays(List<AccountInfoDayStatDTO> days) {
    this.days = days;
  }
}
