package xyz.flysium.web;

import xyz.flysium.dto.UserInfo;

/**
 * 用户信息
 *
 * @author zeno
 */
public class UserInfoHolder {

  private static final ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<>();

  public static UserInfo getUserInfo() {
    return USER_INFO.get();
  }

  public static void setUserInfo(UserInfo value) {
    USER_INFO.set(value);
  }

  public static void remove() {
    USER_INFO.remove();
  }

}
