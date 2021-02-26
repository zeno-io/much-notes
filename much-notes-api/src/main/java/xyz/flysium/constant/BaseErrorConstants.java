package xyz.flysium.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.web.controller.miniprogram.UserAccountInfoController;

/**
 * 错误常量
 *
 * @author zeno
 */
public abstract class BaseErrorConstants {

  protected static final Logger log = LoggerFactory.getLogger(BaseErrorConstants.class);
  /**
   * 错误编码
   */
  protected final String errorCode;
  /**
   * 错误信息格式
   */
  protected final String message;
  /**
   * 是否需要传入参数
   */
  protected final boolean requiredArgument;

  public BaseErrorConstants(String errorCode, String message) {
    this(errorCode, message, false);
  }

  public BaseErrorConstants(String errorCode, String message, boolean requiredArgument) {
    this.errorCode = errorCode;
    this.message = message;
    this.requiredArgument = requiredArgument;
  }

  public String getMessage() {
    if (requiredArgument) {
      log.warn("Method `throwsException(Throwable e)` is unsupported for Errors " + "which required arguments [{}}], "
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

  public abstract void throwsException();

  public abstract void throwsException(Throwable e);

  public abstract void throwsException(Object... args);

  public abstract void throwsException(Throwable e, Object... args);

  public void logException(Logger log) {
    log.error(getMessage());
  }

  public void logException(Logger log, Throwable e) {
    log.error(getMessage(), e);
  }

  public void logException(Logger log, Object... args) {
    log.error(getMessage(args));
  }

  public void logException(Logger log, Throwable e, Object... args) {
    log.error(getMessage(args), e);
  }

  public void logAndThrowException(Logger log) {
    logException(log);
    throwsException();
  }

  public void logAndThrowException(Logger log, Throwable e) {
    logException(log, e);
    throwsException(e);
  }

  public void logAndThrowException(Logger log, Object... args) {
    logException(log, args);
    throwsException(args);
  }

  public void logAndThrowException(Logger log, Throwable e, Object... args) {
    logException(log, e, args);
    throwsException(e, args);
  }

  public void throwsWarning() {
    throwsException();
  }

  public void throwsWarning(Throwable e) {
    throwsException(e);
  }

  public void throwsWarning(Object... args) {
    throwsException(args);
  }

  public void throwsWarning(Throwable e, Object... args) {
    throwsException(e, args);
  }

  public void logWarning(Logger log) {
    log.warn(getMessage());
  }

  public void logWarning(Logger log, Throwable e) {
    log.warn(getMessage(), e);
  }

  public void logWarning(Logger log, Object... args) {
    log.warn(getMessage(args));
  }

  public void logWarning(Logger log, Throwable e, Object... args) {
    log.warn(getMessage(args), e);
  }

  public void logAndThrowWarning(Logger log) {
    logWarning(log);
    throwsWarning();
  }

  public void logAndThrowWarning(Logger log, Throwable e) {
    logWarning(log, e);
    throwsWarning(e);
  }

  public void logAndThrowWarning(Logger log, Object... args) {
    logWarning(log, args);
    throwsWarning(args);
  }

  public void logAndThrowWarning(Logger log, Throwable e, Object... args) {
    logWarning(log, e, args);
    throwsWarning(e, args);
  }

}
