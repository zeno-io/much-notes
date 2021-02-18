package xyz.flysium.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

@ApiModel("记账请求")
public class UserAccountRecordRequest extends FrontEndRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long recordId;

  // ------------------

  @NotNull
  @ApiModelProperty
  private Long money;

  @NotNull
  @ApiModelProperty
  private Byte type;

  @NotNull
  @ApiModelProperty
  private Byte accountType;

  @NotNull
  @ApiModelProperty
  private Long accountBookId;

  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @ApiModelProperty
  private Date time;

  @ApiModelProperty
  private String categoryName;

  @NotNull
  @ApiModelProperty
  private Long categoryId;

  @ApiModelProperty
  private String remark;


  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Long getMoney() {
    return money;
  }

  public void setMoney(Long money) {
    this.money = money;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Byte getAccountType() {
    return accountType;
  }

  public void setAccountType(Byte accountType) {
    this.accountType = accountType;
  }

  public Long getAccountBookId() {
    return accountBookId;
  }

  public void setAccountBookId(Long accountBookId) {
    this.accountBookId = accountBookId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "UserAccountRecordRequest{" + "from=" + from + ", recordId=" + recordId + ", money=" + money + ", type="
      + type + ", accountType=" + accountType + ", accountBookId=" + accountBookId + ", time=" + time
      + ", categoryName='" + categoryName + '\'' + ", categoryId=" + categoryId + ", remark='" + remark + '\'' + '}';
  }
}
