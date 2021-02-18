package xyz.flysium.constant.enums;

/**
 * 用户状态
 *
 * @author zeno
 */
public enum UserStatus {
  /**
   * 封号
   */
  INVALID(0, "封号"),
  /**
   * 正常
   */
  VALID(1, "正常");

  final int key;
  final String name;

  UserStatus(int key, String name) {
    this.key = key;
    this.name = name;
  }

  public int getKey() {
    return key;
  }

  public byte getKeyByte() {
    return (byte) key;
  }

  public String getName() {
    return name;
  }
}
