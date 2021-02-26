package xyz.flysium.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.flysium.dao.repository.StatMapper;
import xyz.flysium.dao.vo.AccountDayStatDO;
import xyz.flysium.dao.vo.AccountMonthStatDO;
import xyz.flysium.dao.vo.AccountYearStatDO;

/**
 * 统计查询
 *
 * @author zeno
 */
@Service
public class StatService {

  @Autowired
  private StatMapper statMapper;

  /**
   * 获取某一些账本的指定天的统计
   */
  public List<AccountDayStatDO> queryAccountDayStatByAccountBookIdList(List<Long> accountBookIds,
    List<LocalDate> times) {
    Objects.requireNonNull(accountBookIds);
    Objects.requireNonNull(times);
    if (CollectionUtils.isEmpty(times)) {
      return Collections.emptyList();
    }
    return statMapper
      .selectAccountDayStatByAccountBookIdList(accountBookIds, times);
  }

  /**
   * 获取某一些账本的指定月的统计
   */
  public AccountMonthStatDO getAccountMonthStatByAccountBookIdList(List<Long> accountBookIds,
    Integer year, Integer monthOfYear) {
    Objects.requireNonNull(accountBookIds);
    Objects.requireNonNull(year);
    Objects.requireNonNull(monthOfYear);
    AccountMonthStatDO stat = statMapper
      .selectAccountMonthStatByAccountBookIdList(accountBookIds, year, monthOfYear);
    if (stat == null) {
      stat = new AccountMonthStatDO();
    }
    return stat;
  }

  /**
   * 获取某一些账本的指定年的细分统计
   */
  public List<AccountYearStatDO> queryAccountYearStatByAccountBookIdList(List<Long> accountBookIds,
    Integer year) {
    Objects.requireNonNull(accountBookIds);
    Objects.requireNonNull(year);
    return statMapper
      .selectAccountYearStatByAccountBookIdList(accountBookIds, year);
  }

}
