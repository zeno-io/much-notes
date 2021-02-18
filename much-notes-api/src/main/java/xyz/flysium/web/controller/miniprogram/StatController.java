package xyz.flysium.web.controller.miniprogram;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.dao.vo.AccountMonthStatDO;
import xyz.flysium.dao.vo.AccountYearStatDO;
import xyz.flysium.dto.AccountMonthStatDTO;
import xyz.flysium.dto.AccountYearStatDTO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.StatService;
import xyz.flysium.web.UserInfoHolder;

/**
 * 统计查询
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/stat")
@Api(value = "StatApi", tags = {"统计"})
public class StatController {

  @Autowired
  private StatService statService;

  @GetMapping("/getTotalWithMonth")
  @ApiOperation("获取某一个账本的指定月的统计")
  public ResultResponse<AccountMonthStatDTO> getTotalWithMonth(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId,
    @Validated @NotNull @DateTimeFormat(pattern = "yyyy-MM") @RequestParam(name = "date") Date date) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("没有账本权限");
    }
    final LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int monthOfYear = localDate.getMonthValue();
    AccountMonthStatDO stat = statService.getAccountMonthStatByAccountBookId(accountBookId, year, monthOfYear);
    AccountMonthStatDTO dto = new AccountMonthStatDTO();
    dto.setZc(stat.getSumSpend());
    dto.setSr(stat.getSumIncome());
    return ResultResponse.success(dto);
  }

  @GetMapping("/getTotalByYear")
  @ApiOperation("获取某一个账本的指定年的细分统计")
  public ResultResponse<List<AccountYearStatDTO>> getTotalByYear(
    @Validated @NotNull @RequestParam(name = "id") Long accountBookId,
    @Validated @NotNull @RequestParam(name = "year") Integer year) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    if (!userInfo.checkAuth(accountBookId)) {
      return ResultResponse.fail("没有账本权限");
    }
    List<AccountYearStatDO> list = statService.queryAccountYearStatByAccountBookId(accountBookId, year);
    List<AccountYearStatDTO> res = list.stream().map(stat -> {
      AccountYearStatDTO dto = new AccountYearStatDTO();
      dto.setZc(stat.getSumSpend());
      dto.setSr(stat.getSumIncome());
      dto.setMonth(stat.getMonth());
      return dto;
    }).collect(Collectors.toList());
    return ResultResponse.success(res);
  }

  @GetMapping("/getTotalByMonth")
  @ApiOperation("获取某一个账本的指定月的细分统计")
  public ResultResponse<Object> getTotalByMonth(@Validated @NotNull @RequestParam(name = "id") Long accountBookId,
    @Validated @NotNull @RequestParam(name = "year") Integer year,
    @Validated @NotNull @RequestParam(name = "month") Integer month) {

    return ResultResponse.success();
  }

}
