package xyz.flysium.web.advice;

import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.exception.ApiException;

/**
 * Exception Handler for {@link org.springframework.stereotype.Controller}
 *
 * @author zeno
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice {

  protected static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
  public ResultResponse<String> illegalExceptionHandler(Exception ex) {
    LOGGER.error("exception with :[{}]", ex.getMessage(), ex);
    return ResultResponse.fail(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResultResponse<String> methodArgumentNotValidException(
    MethodArgumentNotValidException ex) {
    LOGGER.error("exception with :[{}]", ex.getMessage(), ex);
    BindingResult bindingResult = ex.getBindingResult();
    StringBuilder sb = new StringBuilder("校验失败! ");
    String pre = "";
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      sb.append(pre).append(fieldError.getField()).append(": ")
        .append(fieldError.getDefaultMessage());
      pre = "; ";
    }
    return ResultResponse.fail(sb.toString());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  public ResultResponse<String> constraintViolationException(
    ConstraintViolationException ex) {
    LOGGER.error("exception with :[{}]", ex.getMessage(), ex);
    return ResultResponse.fail("校验失败! " + ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResultResponse<String> methodArgumentTypeMismatchException(
    MethodArgumentTypeMismatchException ex) {
    LOGGER.error("exception with :[{}]", ex.getMessage(), ex);
    return ResultResponse.fail("类型不匹配! " + ex.getMessage());
  }

  @ExceptionHandler(value = Exception.class)
  public ResultResponse<String> otherExceptionHandler(Exception ex) {
    LOGGER.error("exception with :[{}]", ex.getMessage(), ex);
    ResultResponse<String> result = ResultResponse.fail("系统内部错误");
    if (ex instanceof DuplicateKeyException) {
      result.setMessage(ex.getMessage().split("###")[1].split(":")[2]);
    } else if (ex instanceof ApiException) {
      ApiException apiException = (ApiException) ex;
      result.setCode(apiException.getErrorCode());
      result.setMessage(apiException.getMessage());
    } else {
      result.setMessage(ex.getMessage());
    }
    return result;
  }

}
