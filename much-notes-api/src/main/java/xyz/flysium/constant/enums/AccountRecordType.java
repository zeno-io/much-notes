package xyz.flysium.constant.enums;

/**
 * 收入还是支出
 *
 * @author zeno
 */
public enum AccountRecordType {
  /**
   * 支出
   */
  SPEND(0, "支出"),
  /**
   * 收入
   */
  INCOME(1, "收入"),
  ;

  final int key;
  final String name;

  AccountRecordType(int key, String name) {
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
