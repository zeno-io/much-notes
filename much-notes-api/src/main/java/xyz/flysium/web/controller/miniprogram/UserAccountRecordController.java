package xyz.flysium.web.controller.miniprogram;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.constant.enums.IsOrNot;
import xyz.flysium.dao.entity.CategoryDO;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.UserAccountBookDO;
import xyz.flysium.dao.entity.UserAccountRecordDO;
import xyz.flysium.dao.vo.AccountDayStatDO;
import xyz.flysium.dto.AccountDayStatsDTO;
import xyz.flysium.dto.AccountInfoDayStatDTO;
import xyz.flysium.dto.CategoryDTO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserAccountBookAuthDTO;
import xyz.flysium.dto.UserAccountRecordDTO;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.dto.request.UserAccountRecordRequest;
import xyz.flysium.service.CategoryService;
import xyz.flysium.service.StatService;
import xyz.flysium.service.UserAccountBookService;
import xyz.flysium.service.UserAccountRecordService;
import xyz.flysium.service.UserService;
import xyz.flysium.support.page.PageSupport;
import xyz.flysium.web.UserInfoHolder;

/**
 * 记账
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/account/record")
@Api(value = "AccountRecordApi", tags = {"记账"})
public class UserAccountRecordController {

  protected static final Logger LOGGER = LoggerFactory.getLogger(UserAccountRecordController.class);

  @Autowired
  private UserAccountRecordService userAccountRecordService;
  @Autowired
  private UserAccountBookService userAccountBookService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private UserService userService;
  @Autowired
  private StatService statService;

  private final Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

  @GetMapping("/checkRecordAuth")
  @ApiOperation("检查账本操作权限")
  public ResultResponse<String> checkRecordAuth(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (!userInfo.checkRecordAuth(accountBookId)) {
      return ResultResponse.fail("无账本操作权限");
    }
    return ResultResponse.success();
  }

  @PostMapping("/addRecord")
  @ApiOperation("新增记账")
  public ResultResponse<Long> addRecord(
    @Validated @NotNull @RequestBody UserAccountRecordRequest record) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[新增记账] addRecord.request [uid={}]: {}", uid, record);
    if (!userInfo.checkRecordAuth(record.getAccountBookId())) {
      return ResultResponse.fail("无账本操作权限");
    }

    UserAccountRecordDO accountRecord = dozerBeanMapper.map(record, UserAccountRecordDO.class);
    accountRecord.setUid(uid);
    accountRecord.setCreator(uid);
    accountRecord.setIsDeleted(IsOrNot.False.getKeyByte());
    Long recordId = userAccountRecordService.addRecord(accountRecord);
    return ResultResponse.success(recordId);
  }

  @PostMapping("/editRecord")
  @ApiOperation("编辑记账")
  public ResultResponse<Long> editRecord(
    @Validated @NotNull @RequestBody UserAccountRecordRequest record) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[编辑记账] editRecord.request [uid={}]: {}", uid, record);
    if (!userInfo.checkRecordAuth(record.getAccountBookId())) {
      return ResultResponse.fail("无账本操作权限");
    }

    Long recordId = record.getRecordId();
    UserAccountRecordDO accountRecord = dozerBeanMapper.map(record, UserAccountRecordDO.class);
    accountRecord.setIsDeleted(IsOrNot.False.getKeyByte());

    boolean b = userAccountRecordService.editRecord(recordId, accountRecord, uid);
    if (!b) {
      return ResultResponse.fail("修改失败");
    }

    return ResultResponse.success(recordId);
  }

  @GetMapping("/deleteRecord")
  @ApiOperation("删除记账")
  public ResultResponse<Long> deleteRecord(
    @Validated @NotNull @RequestParam(name = "recordId") Long recordId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[删除记账] deleteRecord.request [uid={}]: recordId={}", uid, recordId);

    UserAccountRecordDO record = userAccountRecordService.getRecordById(recordId);
    if (record == null) {
      return ResultResponse.fail("删除失败");
    }
    if (!userInfo.checkRecordAuth(record.getAccountBookId())) {
      return ResultResponse.fail("无账本操作权限");
    }
    boolean b = userAccountRecordService.deleteRecord(recordId, uid);
    if (!b) {
      return ResultResponse.fail("修改失败");
    }
    return ResultResponse.success(recordId);
  }

  @GetMapping("/getRecordById")
  @ApiOperation("依据 ID 查询一条记账")
  public ResultResponse<UserAccountRecordDTO> getRecordById(
    @Validated @NotNull @RequestParam(name = "id") Long recordId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    UserAccountRecordDO record = userAccountRecordService.getRecordById(recordId);
    if (record == null) {
      return ResultResponse.fail("获取失败");
    }
    if (!userInfo.checkRecordAuth(record.getAccountBookId())) {
      return ResultResponse.fail("无账本权限");
    }
    UserAccountBookDO book = userAccountBookService
      .getAccountBookById(record.getAccountBookId());
    if (book == null) {
      return ResultResponse.fail("账本不存在或已被删除");
    }
    CategoryDO category = categoryService.getCategoryById(record.getCategoryId());
    UserAccountRecordDTO dto = dozerBeanMapper.map(record, UserAccountRecordDTO.class);
    dto.setAccountBookName(book.getName());
    dto.setCategory((category == null) ? null : dozerBeanMapper.map(category, CategoryDTO.class));
    if (record.getCreator() != null) {
      NoteUserDO user = userService.getUnFilterUserById(record.getCreator());
      if (user != null) {
        dto.setCreatorName(user.getNickname());
      }
    }
    if (record.getUpdater() != null) {
      NoteUserDO user = userService.getUnFilterUserById(record.getUpdater());
      if (user != null) {
        dto.setUpdaterName(user.getNickname());
      }
    }

    return ResultResponse.success(dto);
  }

  @GetMapping("/getListByAccountBookId")
  @ApiOperation("分页查询某一个账本的记账记录")
  public ResultResponse<AccountDayStatsDTO> getListByAccountBookId(
    @RequestParam(name = "page", required = false) Integer pageNumber,
    @RequestParam(name = "size", required = false) Integer pageSize,
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("无账本权限");
    }
    List<Long> accountBookIdList = (accountBookId == -1L) ?
      userInfo.getAuthList().stream()
        .map(UserAccountBookAuthDTO::getAccountBookId).distinct().collect(Collectors.toList())
      : Collections.singletonList(accountBookId);
    PageInfo<UserAccountRecordDO> pageInfo = userAccountRecordService
      .getRecordByAccountBookIdList(pageNumber, pageSize, accountBookIdList);
    List<UserAccountBookDO> books = userAccountBookService
      .queryAccountBookListByUid(uid);
    Map<Long, String> booksIdToName = books.stream()
      .collect(Collectors.toMap(UserAccountBookDO::getId, UserAccountBookDO::getName));
    PageInfo<UserAccountRecordDTO> dtoPageInfo = PageSupport
      .transformTo(pageInfo, r -> {
        UserAccountRecordDTO dto = dozerBeanMapper.map(r, UserAccountRecordDTO.class);
        dto.setAccountBookName(booksIdToName.getOrDefault(dto.getAccountBookId(), "N/A"));
        return dto;
      });
    List<LocalDate> times = pageInfo.getList().stream()
      .map(
        r -> LocalDateTime.ofInstant(r.getTime().toInstant(), ZoneId.systemDefault()).toLocalDate())
      .collect(Collectors.toList());
    List<AccountDayStatDO> stats = statService
      .queryAccountDayStatByAccountBookIdList(accountBookIdList, times);

    return ResultResponse.success(getAccountDayStatsDTO(dtoPageInfo, stats));
  }

  private AccountDayStatsDTO getAccountDayStatsDTO(PageInfo<UserAccountRecordDTO> pageInfo,
    List<AccountDayStatDO> stats) {
    AccountDayStatsDTO dto = new AccountDayStatsDTO();
    dto.setTotal(pageInfo.getTotal());
    dto.setData(pageInfo.getList());
    dto.setDays(stats.stream().map(stat -> {
      AccountInfoDayStatDTO infoDayStatDTO = new AccountInfoDayStatDTO();
      infoDayStatDTO.setZc(stat.getSumSpend());
      infoDayStatDTO.setSr(stat.getSumIncome());
      infoDayStatDTO.setTime(stat.getTime());
      return infoDayStatDTO;
    }).collect(Collectors.toList()));
    return dto;
  }

}
