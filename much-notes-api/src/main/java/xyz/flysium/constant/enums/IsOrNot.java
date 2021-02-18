package xyz.flysium.constant.enums;

/**
 * yes or no
 *
 * @author zeno
 */
public enum IsOrNot {
  /**
   * yes
   */
  True(1, "yes"),
  /**
   * no
   */
  False(0, "no");

  final int key;
  final String name;

  IsOrNot(int key, String name) {
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
