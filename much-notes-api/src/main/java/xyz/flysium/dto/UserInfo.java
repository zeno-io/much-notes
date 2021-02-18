package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import xyz.flysium.constant.enums.IsOrNot;
import xyz.flysium.constant.enums.UserType;

/**
 * 用户信息
 *
 * @author zeno
 */
@ApiModel("用户信息")
public class UserInfo implements java.io.Serializable {

  private static final long serialVersionUID = -1L;

  @ApiModelProperty
  private final String openId;

  @ApiModelProperty
  private final String unionId;

  @ApiModelProperty
  private final Long uid;

  @ApiModelProperty
  private String token;

  @ApiModelProperty
  private Integer type;

  @ApiModelProperty
  private List<UserAccountBookAuthDTO> authList;

  public UserInfo(String openId, String unionId, Long uid) {
    this.openId = openId;
    this.unionId = unionId;
    this.uid = uid;
  }

  /**
   * 检查是否有账本的查看权限
   */
  public boolean checkAuth(Long accountBookId) {
    if (Objects.equals(type, UserType.ADMIN_USER.getKey())) {
      return true;
    }
    return CollectionUtils.emptyIfNull(authList).stream()
      .anyMatch(auth -> Objects.equals(auth.getAccountBookId(), accountBookId));
  }

  /**
   * 检查是否有账本的管理员权限
   */
  public boolean checkAdminAuth(Long accountBookId) {
    if (Objects.equals(type, UserType.ADMIN_USER.getKey())) {
      return true;
    }
    return CollectionUtils.emptyIfNull(authList).stream()
      .anyMatch(auth -> Objects.equals(auth.getAccountBookId(), accountBookId)
        && IsOrNot.True.getKeyByte() == auth.getIsAdmin());
  }

  public String getOpenId() {
    return openId;
  }

  public String getUnionId() {
    return unionId;
  }

  public Long getUid() {
    return uid;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public List<UserAccountBookAuthDTO> getAuthList() {
    return authList;
  }

  public void setAuthList(List<UserAccountBookAuthDTO> authList) {
    this.authList = authList;
  }
}
