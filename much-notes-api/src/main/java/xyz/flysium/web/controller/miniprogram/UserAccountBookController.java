package xyz.flysium.web.controller.miniprogram;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.constant.enums.AccountBookType;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.UserAccountBookAuthDO;
import xyz.flysium.dao.entity.UserAccountBookDO;
import xyz.flysium.dto.AccountUsersDTO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserAccountBookAuthDTO;
import xyz.flysium.dto.UserAccountBookDTO;
import xyz.flysium.dto.UserAccountBookWithCountDTO;
import xyz.flysium.dto.UserDTO;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.ShareService;
import xyz.flysium.service.UserAccountBookService;
import xyz.flysium.service.UserService;
import xyz.flysium.web.UserInfoHolder;

/**
 * 账本
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/account/book")
@Api(value = "AccountBookApi", tags = {"账本"})
public class UserAccountBookController {

  protected static final Logger LOGGER = LoggerFactory.getLogger(UserAccountBookController.class);

  @Autowired
  private UserAccountBookService userAccountBookService;
  @Autowired
  private UserService userService;
  @Autowired
  private ShareService shareService;

  private final Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

  public UserAccountBookDO all(Long uid) {
    UserAccountBookDO all = new UserAccountBookDO();
    all.setId(-1L);
    all.setUid(uid);
    all.setName("所有");
    all.setType(AccountBookType.NORMAL.getKeyByte());
    return all;
  }

  @GetMapping("/addNormal")
  @ApiOperation("创建账本")
  public ResultResponse<Long> addNormalAccountBook(
    @Validated @NotBlank @RequestParam(name = "name") String name) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[创建账本] addNormalAccountBook.request [uid={}]: name={}", uid, name);

    Long id = userAccountBookService.addNormalAccountBook(name, uid);
    return ResultResponse.success(id);
  }

  @GetMapping("/getShareKey")
  @ApiOperation("生成一个分享的临时邀请链接")
  public ResultResponse<String> getShareKey(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER
      .debug("[生成一个分享的临时邀请链接] getShareKey.request [uid={}]: accountBookId={}", uid, accountBookId);
    if (!userInfo.checkAdminAuth(accountBookId)) {
      return ResultResponse.fail("您没有管理员权限");
    }
    String key = shareService.update(accountBookId);

    return ResultResponse.success(key);
  }

  @GetMapping("/addUserToAccountBook")
  @ApiOperation("用户加入到账本")
  public ResultResponse<Long> addUserToAccountBook(
    @Validated @NotBlank @RequestParam(name = "key") String key) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[用户加入到账本] addUserToAccountBook.request [uid={}]: key={}", uid, key);

    Long accountBookId = shareService.getAccountBookId(key);
    if (accountBookId == null) {
      return ResultResponse.fail("添加失败");
    }
    if (userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("您已经加入此账本了");
    }
    try {
      userAccountBookService.addUserToAccountBook(accountBookId, uid, uid);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail("操作失败");
    }

    return ResultResponse.success(accountBookId);
  }

  @GetMapping("/removeUserFromAccountBook")
  @ApiOperation("将用户从账本移除")
  public ResultResponse<Object> removeUserFromAccountBook(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId,
    @Validated @NotNull @RequestParam(name = "uid") Long thatUid) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER
      .debug("[将用户从账本移除] addUserToAccountBook.request [uid={}]: accountBookId={}, uid={}", uid,
        accountBookId, thatUid);
    if (thatUid.equals(uid)) {
      return ResultResponse.fail("不能将自己移除");
    }
    if (!userInfo.checkAdminAuth(accountBookId)) {
      return ResultResponse.fail("您没有管理员权限");
    }
    try {
      boolean b = userAccountBookService.removeUserFromAccountBook(accountBookId, thatUid, uid);
      if (!b) {
        return ResultResponse.fail("操作失败");
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail("操作失败");
    }

    return ResultResponse.success();
  }

  @GetMapping("/exitFromAccountBook")
  @ApiOperation("用户主动退出")
  public ResultResponse<Long> exitFromAccountBook(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER
      .debug("[用户主动退出] exitFromAccountBook.request [uid={}]: accountBookId={}", uid, accountBookId);
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.success();
    }
    if (userInfo.checkAdminAuth(accountBookId)) {
      return ResultResponse.fail("管理员不可退出");
    }
    try {
      boolean b = userAccountBookService.removeUserFromAccountBook(accountBookId, uid, uid);
      if (!b) {
        return ResultResponse.fail("操作失败");
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail("操作失败");
    }

    return ResultResponse.success();
  }

  @GetMapping("/changeAdmin")
  @ApiOperation("移交管理员")
  public ResultResponse<Long> changeAdmin(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId,
    @Validated @NotNull @RequestParam(name = "uid") Long thatUid) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER
      .debug("[移交管理员] changeAdmin.request [uid={}]: accountBookId={}, uid={}", uid, accountBookId,
        thatUid);
    if (!userInfo.checkAdminAuth(accountBookId)) {
      return ResultResponse.fail("您没有管理员权限");
    }
    try {
      boolean b = userAccountBookService.changeAdmin(accountBookId, uid, thatUid);
      if (!b) {
        return ResultResponse.fail("操作失败");
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail("操作失败");
    }

    return ResultResponse.success();
  }

  @GetMapping("/getAccountBookById")
  @ApiOperation("获取单个账本信息")
  public ResultResponse<UserAccountBookWithCountDTO> getAccountBookById(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    UserAccountBookDO book = null;
    long count = 0L;
    // 用户的所有账本（虚拟）
    if (accountBookId == -1) {
      book = all(uid);
      count = 1L;
    } else {
      if (!userInfo.checkAuth(accountBookId)) {
        return ResultResponse.fail("无查看权限");
      }
      book = userAccountBookService.getAccountBookById(accountBookId);
      count = userAccountBookService.countAccountBookById(accountBookId);
    }
    if (book == null) {
      return ResultResponse.fail("账本不存在或已被删除");
    }

    UserAccountBookWithCountDTO accountBookDTO = dozerBeanMapper
      .map(book, UserAccountBookWithCountDTO.class);
    accountBookDTO.setCount(count);
    return ResultResponse.success(accountBookDTO);
  }

  @GetMapping("/getList")
  @ApiOperation("获取用户下的所有账本信息")
  public ResultResponse<List<UserAccountBookDTO>> getAccountBookList(
    @Validated @NotNull @RequestParam(name = "all", required = false) Boolean all) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();

    List<UserAccountBookDO> books = userAccountBookService.queryAccountBookListByUid(uid);
    if (all != null && all) {
      books.add(0, all(uid));
    }
    return ResultResponse.success(
      books.stream().map(book -> dozerBeanMapper.map(book, UserAccountBookDTO.class))
        .collect(Collectors.toList()));
  }

  @GetMapping("/getAccountBookUsersById")
  @ApiOperation("获取当前账本的用户列表")
  public ResultResponse<AccountUsersDTO> getAccountBookUsersById(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("无查看权限");
    }
    List<UserAccountBookAuthDO> auths = userAccountBookService
      .queryAccountBookUsersById(accountBookId);
    List<Long> uidList = auths.stream().map(UserAccountBookAuthDO::getUid).distinct()
      .collect(Collectors.toList());
    List<NoteUserDO> users = userService.getUsersByIds(uidList);

    AccountUsersDTO dto = new AccountUsersDTO();
    dto.setAuths(auths.stream().map(auth -> dozerBeanMapper.map(auth, UserAccountBookAuthDTO.class))
      .collect(Collectors.toList()));
    dto.setUsers(users.stream().map(user -> dozerBeanMapper.map(user, UserDTO.class))
      .collect(Collectors.toList()));
    return ResultResponse.success(dto);
  }

}
