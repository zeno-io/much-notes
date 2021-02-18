package xyz.flysium.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.exception.ApiException;

/**
 * Errors
 *
 * @author zeno
 */
public enum ErrorConstants {
  /* */
  GET_WX_UNION_ID_FAILED("501", "获取授权失败"),
  ;
  protected static final Logger LOGGER = LoggerFactory.getLogger(ErrorConstants.class);
  /**
   * 错误编码
   */
  final String errorCode;
  /**
   * 错误信息格式
   */
  final String message;
  /**
   * 是否需要传入参数
   */
  final boolean requiredArgument;

  ErrorConstants(String errorCode, String message) {
    this(errorCode, message, false);
  }

  ErrorConstants(String errorCode, String message, boolean requiredArgument) {
    this.errorCode = errorCode;
    this.message = message;
    this.requiredArgument = requiredArgument;
  }

  public String getMessage() {
    if (requiredArgument) {
      LOGGER.warn("Method `throwsException(Throwable e)` is unsupported for Errors "
        + "which required arguments [{}}], "
        + "please instead of using `throwsException(Object... args)` "
        + "or `throwsException(Throwable e, Object... args)`", message);
    }
    return message;
  }

  public String getMessage(Object... arguments) {
    if (requiredArgument) {
      FormattingTuple formattingTuple = MessageFormatter.arrayFormat(message, arguments);
      return formattingTuple.getMessage();
    }
    return message;
  }

  public void throwsException() throws ApiException {
    throw new ApiException(errorCode, getMessage());
  }

  public void throwsException(Throwable e) throws ApiException {
    throw new ApiException(errorCode, getMessage(), e);
  }

  public void throwsException(Object... args) throws ApiException {
    throw new ApiException(errorCode, getMessage(args));
  }

  public void throwsException(Throwable e, Object... args) throws ApiException {
    throw new ApiException(errorCode, getMessage(args), e);
  }

  public void logException(Logger log) throws ApiException {
    log.error(getMessage());
  }

  public void logException(Logger log, Throwable e) throws ApiException {
    log.error(getMessage(), e);
  }

  public void logException(Logger log, Object... args) throws ApiException {
    log.error(getMessage(args));
  }

  public void logException(Logger log, Throwable e, Object... args) throws ApiException {
    log.error(getMessage(args), e);
  }

  public void logAndThrowException(Logger log) throws ApiException {
    logException(log);
    throwsException();
  }

  public void logAndThrowException(Logger log, Throwable e) throws ApiException {
    logException(log, e);
    throwsException(e);
  }

  public void logAndThrowException(Logger log, Object... args) throws ApiException {
    logException(log, args);
    throwsException(args);
  }

  public void logAndThrowException(Logger log, Throwable e, Object... args) throws ApiException {
    logException(log, e, args);
    throwsException(e, args);
  }

  public <T> ResultResponse<T> toResultResponse() {
    ResultResponse<T> response = new ResultResponse<>();
    response.failed(errorCode, getMessage());
    return response;
  }

  public <T> ResultResponse<T> toResultResponse(Object... args) {
    ResultResponse<T> response = new ResultResponse<>();
    response.failed(errorCode, getMessage(args));
    return response;
  }

}
