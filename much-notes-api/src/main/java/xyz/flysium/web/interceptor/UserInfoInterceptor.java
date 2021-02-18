package xyz.flysium.web.interceptor;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.flysium.constant.MuchNotesConstants;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.UserAccountBookAuthDO;
import xyz.flysium.dto.UserAccountBookAuthDTO;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.TokenService;
import xyz.flysium.service.UserAccountBookService;
import xyz.flysium.service.UserService;
import xyz.flysium.web.UserInfoHolder;

/**
 * 用户鉴权拦截器
 *
 * @author zeno
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {

  protected static final Logger LOGGER = LoggerFactory.getLogger(UserInfoInterceptor.class);

  @Autowired
  private TokenService tokenService;
  @Autowired
  private UserService userService;
  @Autowired
  private UserAccountBookService userAccountBookService;

  private final Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

  @Override
  public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws Exception {

    String token = request.getHeader(MuchNotesConstants.HTTP_HEADER_TOKEN);
    if (StringUtils.isEmpty(token)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      LOGGER.warn("request={} is lack of token !",
        request.getRequestURI());
      return false;
    }
    UserInfo userInfo = tokenService.getUserInfo(token);
    if (userInfo == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      LOGGER.warn("request={} is unauthorized !", request.getRequestURI());
      return false;
    }
    NoteUserDO user = userService.getUserById(userInfo.getUid());
    if (user == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      LOGGER.warn("request={} by uid={} is unauthorized !",
        request.getRequestURI(), userInfo.getUid());
      return false;
    }
    List<UserAccountBookAuthDO> authList = userAccountBookService
      .queryAccountBookUsersByUid(userInfo.getUid());

    userInfo.setType(user.getType());
    userInfo.setAuthList(authList.stream()
      .map(auth -> dozerBeanMapper.map(auth, UserAccountBookAuthDTO.class))
      .collect(Collectors.toList()));
    UserInfoHolder.setUserInfo(userInfo);
    return true;
  }

}
