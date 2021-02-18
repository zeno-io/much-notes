package xyz.flysium.exception;

/**
 * Api Exception.
 *
 * @author zeno
 */
public class ApiException extends RuntimeException implements java.io.Serializable {

  private static final long serialVersionUID = -1L;

  protected final String errorCode;

  public ApiException(String errorCode) {
    this.errorCode = errorCode;
  }

  public ApiException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public ApiException(String errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public ApiException(String errorCode, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ApiException(String errorCode, String message, Throwable cause, boolean enableSuppression,
    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

}
