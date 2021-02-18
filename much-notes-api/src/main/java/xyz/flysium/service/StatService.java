package xyz.flysium.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
   * 获取某一个账本的指定天的统计
   */
  public List<AccountDayStatDO> queryAccountDayStatByAccountBookId(Long accountBookId,
    Set<LocalDate> times) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(times);
    if (CollectionUtils.isEmpty(times)) {
      return Collections.emptyList();
    }
    return statMapper
      .selectAccountDayStatByAccountBookId(accountBookId, times);
  }

  /**
   * 获取某一个账本的指定月的统计
   */
  public AccountMonthStatDO getAccountMonthStatByAccountBookId(Long accountBookId,
    Integer year, Integer monthOfYear) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(year);
    Objects.requireNonNull(monthOfYear);
    return statMapper
      .selectAccountMonthStatByAccountBookId(accountBookId, year, monthOfYear);
  }

  /**
   * 获取某一个账本的指定年的细分统计
   */
  public List<AccountYearStatDO> queryAccountYearStatByAccountBookId(Long accountBookId,
    Integer year) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(year);
    return statMapper
      .selectAccountYearStatByAccountBookId(accountBookId, year);
  }


}
