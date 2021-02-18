package xyz.flysium.constant.enums;

/**
 * 用户类型
 *
 * @author zeno
 */
public enum UserType {
  /**
   * 普通用户
   */
  NORMAL_USER(1, "普通用户"),
  /**
   * VIP用户
   */
  VIP_USER(5, "VIP用户"),
  /**
   * 管理员
   */
  ADMIN_USER(10, "管理员"),
  ;

  final int key;
  final String name;

  UserType(int key, String name) {
    this.key = key;
    this.name = name;
  }

  public int getKey() {
    return key;
  }

  public String getName() {
    return name;
  }
}
