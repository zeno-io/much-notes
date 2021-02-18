package xyz.flysium.web.controller.miniprogram;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.constant.ErrorConstants;
import xyz.flysium.constant.enums.UserStatus;
import xyz.flysium.constant.enums.UserType;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.TokenService;
import xyz.flysium.service.UserService;

/**
 * 微信
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/wx")
@Api(value = "WxChatApi", tags = {"微信"})
public class WxController {

  protected static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

  @Autowired
  private WxMaService wxService;
  @Autowired
  private UserService userService;
  @Autowired
  private TokenService tokenService;

  @GetMapping("/validate")
  @ApiOperation("验证消息的确来自微信服务器")
  @ResponseBody
  public void validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
    String signature = request.getParameter("signature");
    // 时间戳
    String timestamp = request.getParameter("timestamp");
    // 随机数
    String nonce = request.getParameter("nonce");
    // 随机字符串
    String echostr = request.getParameter("echostr");

    try (PrintWriter out = response.getWriter();) {
      // 通过检验signature对请求进行校验，若校验成功则原样返回 echostr，否则接入失败
      if (wxService.checkSignature(nonce, timestamp, signature)) {
        out.print(echostr);
        out.flush();
      }
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  @GetMapping("/login")
  @ApiOperation("登录")
  public ResultResponse<UserInfo> login(@Validated @NotBlank @ApiParam @RequestParam("code") String code,
    @ApiParam(required = false) @RequestParam(value = "encryptedData", required = false) String encryptedData,
    @ApiParam(required = false) @RequestParam(value = "iv", required = false) String iv,
    @ApiParam(required = false) @RequestParam(value = "headImg", required = false) String headImg,
    @ApiParam(required = false) @RequestParam(value = "nickName", required = false) String nickName) {
    LOGGER
      .debug("login.request : encryptedData={}, iv={}, headImg={}, nickName={}", encryptedData, iv, headImg, nickName);

    WxMaJscode2SessionResult session = null;
    try {
      session = wxService.getUserService().getSessionInfo(code);
    } catch (WxErrorException e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail(e.toString());
    }

    // 对用户未关注公众号或者没使用过微信登陆的情况下，直接获取不到 unionid，要解密数据获取
    WxMaUserInfo wxMaUserInfo = null;
    if (StringUtils.isNotEmpty(encryptedData) && StringUtils.isNotEmpty(iv)) {
      try {
        // 解密用户信息
        wxMaUserInfo = wxService.getUserService().getUserInfo(session.getSessionKey(), encryptedData, iv);
      } catch (WxRuntimeException e) {
        LOGGER.error(e.getMessage(), e);
        return ErrorConstants.GET_WX_UNION_ID_FAILED.toResultResponse();
      }
    }
    if (wxMaUserInfo == null) {
      return ErrorConstants.GET_WX_UNION_ID_FAILED.toResultResponse();
    }
    NoteUserDO user = userService.getUnFilterUserByUnionId(wxMaUserInfo.getUnionId());
    if (user == null) {
      user = new NoteUserDO();
      // 默认为普通用户
      user.setType(UserType.NORMAL_USER.getKey());
      user.setOpenId(wxMaUserInfo.getOpenId());
      user.setUnionId(wxMaUserInfo.getUnionId());
      user.setSessionKey(session.getSessionKey());
      user.setNickname(nickName);
      user.setHeadImg(headImg);
      user.setStatus(UserStatus.VALID.getKey());
      userService.addUser(user);
    } else if (Objects.equals(user.getStatus(), UserStatus.INVALID.getKey())) {
      return ResultResponse.fail("已封号");
    }
    Long uid = user.getId();
    UserInfo userInfo = new UserInfo(wxMaUserInfo.getOpenId(), wxMaUserInfo.getUnionId(), uid);
    userInfo.setType(user.getType());
    String token = tokenService.update(uid, wxMaUserInfo.getUnionId(), userInfo);
    userInfo.setToken(token);
    return ResultResponse.success(userInfo);
  }

}
