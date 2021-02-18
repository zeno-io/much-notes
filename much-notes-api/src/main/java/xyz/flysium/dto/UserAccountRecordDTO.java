package xyz.flysium.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("记账记录")
public class UserAccountRecordDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long id;

  @ApiModelProperty
  private String uid;

  @ApiModelProperty
  private Long money;

  @ApiModelProperty
  private Byte type;

  @ApiModelProperty
  private Byte accountType;

  @ApiModelProperty
  private Long accountBookId;

  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
  @ApiModelProperty
  private Date time;

  @ApiModelProperty
  private String categoryName;

  @ApiModelProperty
  private Long categoryId;

  @ApiModelProperty
  private String remark;

  // ------------------

  @ApiModelProperty
  private CategoryDTO category;

  @ApiModelProperty
  private String nickName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public CategoryDTO getCategory() {
    return category;
  }

  public void setCategory(CategoryDTO category) {
    this.category = category;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
}
