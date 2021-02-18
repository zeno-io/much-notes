package xyz.flysium.constant.enums;

/**
 * 账本类型
 *
 * @author zeno
 */
public enum AccountBookType {
  /**
   * 普通
   */
  NORMAL(0, "普通"),
  /**
   * AA账本
   */
  AA(5, "AA账本"),
  ;

  final int key;
  final String name;

  AccountBookType(int key, String name) {
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
