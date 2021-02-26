package xyz.flysium.constant;

import xyz.flysium.exception.ApiException;

/**
 * Errors
 *
 * @author zeno
 */
public final class ErrorConstants extends BaseErrorConstants {
  /* */
  public static final ErrorConstants GET_WX_UNION_ID_FAILED = new ErrorConstants("501", "获取授权失败");

  ErrorConstants(String errorCode, String message) {
    super(errorCode, message, false);
  }

  ErrorConstants(String errorCode, String message, boolean requiredArgument) {
    super(errorCode, message, false);
  }

  @Override
  public void throwsException() throws ApiException {
    throw new ApiException(errorCode, getMessage());
  }

  @Override
  public void throwsException(Throwable e) throws ApiException {
    throw new ApiException(errorCode, getMessage(), e);
  }

  @Override
  public void throwsException(Object... args) throws ApiException {
    throw new ApiException(errorCode, getMessage(args));
  }

  @Override
  public void throwsException(Throwable e, Object... args) throws ApiException {
    throw new ApiException(errorCode, getMessage(args), e);
  }

}
