package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("带统计的账本信息")
public class UserAccountBookWithCountDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long id;

  @ApiModelProperty
  private Long uid;

  @ApiModelProperty
  private String name;

  @ApiModelProperty
  private Byte type;

  private Date createTime;

  private Date updateTime;

  @ApiModelProperty
  private String remark;

  // ------------------

  @ApiModelProperty
  private Long count;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
