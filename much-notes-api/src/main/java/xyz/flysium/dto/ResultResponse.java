package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应出参
 *
 * @author zeno
 */
@ApiModel("响应出参")
public class ResultResponse<T> implements java.io.Serializable {

  private static final long serialVersionUID = -1L;

  protected static final ResultResponse<Object> SUCCESS_RESULT = ResultResponse
    .success((Object) null);

  @ApiModelProperty(value = "服务器响应是否成功", required = true)
  protected boolean success = Boolean.FALSE;

  @ApiModelProperty(value = "返回编码", required = true)
  protected String code;

  @ApiModelProperty(value = "返回信息，文本形式", required = false)
  protected String message;

  @ApiModelProperty(value = "返回结果数据", required = false)
  protected T result;

  public ResultResponse() {
  }

  public ResultResponse(String code, String message) {
    this(false, code, message, null);
  }

  public ResultResponse(boolean success, String code, String message) {
    this(success, code, message, null);
  }

  public ResultResponse(boolean success, String code, String message, T result) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public static <T> ResultResponse<T> success() {
    return success(null);
  }

  public static <T> ResultResponse<T> success(T resultObject) {
    return new ResultResponse<>(true, "100", "success", resultObject);
  }

  public static <T> ResultResponse<T> success(String msg, T resultObject) {
    return new ResultResponse<>(true, "100", msg, resultObject);
  }

  public static <T> ResultResponse<T> fail(String msg) {
    return new ResultResponse<>(false, "500", msg);
  }

  public void succeed(T resultObject) {
    success = true;
    code = "100";
    this.message = "success";
    this.result = resultObject;
  }

  public void failed(String msg) {
    failed("500", msg);
  }

  public void failed(String code, String msg) {
    success = false;
    this.code = code;
    this.message = msg;
  }

  /**
   * 是否成功
   *
   * @return 是否成功
   */
  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "ResultResponse{" +
      "success=" + success +
      ", code='" + code + '\'' +
      ", message='" + message + '\'' +
      ", result=" + result +
      '}';
  }

}
