package xyz.flysium.constant.enums;

/**
 * 用户关系
 *
 * @author zeno
 */
public enum UserRelationshipType {
  /**
   * 家庭
   */
  FAMILY(0, "家庭"),
  ;

  final int key;
  final String name;

  UserRelationshipType(int key, String name) {
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
