package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("账本的用户列表")
public class AccountUsersDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private List<UserAccountBookAuthDTO> auths;

  @ApiModelProperty
  private List<UserDTO> users;

  public List<UserAccountBookAuthDTO> getAuths() {
    return auths;
  }

  public void setAuths(List<UserAccountBookAuthDTO> auths) {
    this.auths = auths;
  }

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }
}
