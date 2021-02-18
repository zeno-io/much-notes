package xyz.flysium.web.controller.miniprogram;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import xyz.flysium.dao.entity.UserAccountRecordDO;
import xyz.flysium.dao.vo.AccountDayStatDO;
import xyz.flysium.dto.AccountDayStatDTO;
import xyz.flysium.dto.CategoryDTO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserAccountRecordDTO;
import xyz.flysium.dto.request.UserAccountRecordRequest;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.CategoryService;
import xyz.flysium.service.StatService;
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
  private CategoryService categoryService;
  @Autowired
  private UserService userService;
  @Autowired
  private StatService statService;

  private final Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

  @PostMapping("/addRecord")
  @ApiOperation("新增记账")
  public ResultResponse<Long> addRecord(@Validated @NotNull @RequestBody UserAccountRecordRequest record) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[新增记账] addRecord.request [uid={}]: {}", uid, record);
    if (!userInfo.checkAuth(record.getAccountBookId())) {
      return ResultResponse.fail("没有账本权限");
    }

    UserAccountRecordDO accountRecord = dozerBeanMapper.map(record, UserAccountRecordDO.class);
    accountRecord.setUid(uid);
    accountRecord.setIsDeleted(IsOrNot.False.getKeyByte());
    Long recordId = userAccountRecordService.addRecord(accountRecord);
    return ResultResponse.success(recordId);
  }

  @PostMapping("/editRecord")
  @ApiOperation("编辑记账")
  public ResultResponse<Long> editRecord(@Validated @NotNull @RequestBody UserAccountRecordRequest record) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[编辑记账] editRecord.request [uid={}]: {}", uid, record);
    if (!userInfo.checkAuth(record.getAccountBookId())) {
      return ResultResponse.fail("没有账本权限");
    }

    Long recordId = record.getRecordId();
    UserAccountRecordDO accountRecord = dozerBeanMapper.map(record, UserAccountRecordDO.class);
    accountRecord.setUid(uid);
    accountRecord.setIsDeleted(IsOrNot.False.getKeyByte());

    boolean b = userAccountRecordService.editRecord(recordId, accountRecord);
    if (!b) {
      return ResultResponse.fail("修改失败");
    }

    return ResultResponse.success(recordId);
  }

  @GetMapping("/deleteRecord")
  @ApiOperation("删除记账")
  public ResultResponse<Long> deleteRecord(@Validated @NotNull @RequestParam(name = "recordId") Long recordId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[删除记账] deleteRecord.request [uid={}]: recordId={}", uid, recordId);

    UserAccountRecordDO record = userAccountRecordService.getRecordById(recordId);
    if (record == null) {
      return ResultResponse.fail("删除失败");
    }
    if (!userInfo.checkAuth(record.getAccountBookId())) {
      return ResultResponse.fail("没有账本权限");
    }
    boolean b = userAccountRecordService.deleteRecord(recordId);
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
    if (!userInfo.checkAuth(record.getAccountBookId())) {
      return ResultResponse.fail("没有账本权限");
    }
    CategoryDO category = categoryService.getCategoryById(record.getCategoryId());
    NoteUserDO user = userService.getUnFilterUserById(record.getUid());

    UserAccountRecordDTO dto = dozerBeanMapper.map(record, UserAccountRecordDTO.class);
    dto.setCategory(dozerBeanMapper.map(category, CategoryDTO.class));
    dto.setNickName(user.getNickname());

    return ResultResponse.success(dto);
  }

  @GetMapping("/getListByAccountBookId")
  @ApiOperation("分页查询某一个账本的记账记录")
  public ResultResponse<Map<String, Object>> getListByAccountBookId(
    @RequestParam(name = "page", required = false) Integer pageNumber,
    @RequestParam(name = "size", required = false) Integer pageSize,
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("没有账本权限");
    }
    PageInfo<UserAccountRecordDO> pageInfo = userAccountRecordService
      .getRecordByAccountBookId(pageNumber, pageSize, accountBookId);
    PageInfo<UserAccountRecordDTO> dtoPageInfo = PageSupport
      .transformTo(pageInfo, r -> dozerBeanMapper.map(r, UserAccountRecordDTO.class));
    Set<LocalDate> times = pageInfo.getList().stream()
      .map(r -> LocalDateTime.ofInstant(r.getTime().toInstant(), ZoneId.systemDefault()).toLocalDate())
      .collect(Collectors.toSet());
    List<AccountDayStatDO> stats = statService.queryAccountDayStatByAccountBookId(accountBookId, times);

    Map<String, Object> res = new HashMap<>(16);
    res.put("total", dtoPageInfo.getTotal());
    res.put("data", dtoPageInfo.getList());
    res.put("days", stats.stream().map(stat -> {
      AccountDayStatDTO dto = new AccountDayStatDTO();
      dto.setZc(stat.getSumSpend());
      dto.setSr(stat.getSumIncome());
      dto.setTime(stat.getTime());
      return dto;
    }).collect(Collectors.toList()));
    return ResultResponse.success(res);
  }

}
