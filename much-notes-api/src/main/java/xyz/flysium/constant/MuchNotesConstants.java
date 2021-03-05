package xyz.flysium.constant;

/**
 * 常量类
 *
 * @author zeno
 */
public final class MuchNotesConstants {

  private MuchNotesConstants() {
  }

  /* HTTP Header */
  public static final String HTTP_HEADER_TOKEN = "token";
  /* Cache */
  public static final String DIRECTORY_VAR_CACHE = "/var/cache/much_notes";

  /* 小程序Token失效时间，单位秒 */
  public static final long WX_TOKEN_TIME_OUT = 7 * 24 * 60 * 60;
  /* 账本分享失效时间，单位秒 */
  public static final long ACCOUNT_SHARE_TIME_OUT = 2 * 24 * 60 * 60;

}
