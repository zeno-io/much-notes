package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("用户账本权限")
public class UserAccountBookAuthDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long accountBookId;

  @ApiModelProperty
  private Byte isAdmin;

  @ApiModelProperty
  private Byte block;

  @ApiModelProperty
  private String remark;

  public Long getAccountBookId() {
    return accountBookId;
  }

  public void setAccountBookId(Long accountBookId) {
    this.accountBookId = accountBookId;
  }

  public Byte getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Byte isAdmin) {
    this.isAdmin = isAdmin;
  }

  public Byte getBlock() {
    return block;
  }

  public void setBlock(Byte block) {
    this.block = block;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
