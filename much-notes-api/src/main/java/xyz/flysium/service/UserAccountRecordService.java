package xyz.flysium.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.flysium.constant.enums.AccountRecordType;
import xyz.flysium.constant.enums.IsOrNot;
import xyz.flysium.dao.entity.UserAccountRecordDO;
import xyz.flysium.dao.entity.UserAccountRecordDOExample;
import xyz.flysium.dao.repository.UserAccountRecordDOMapper;
import xyz.flysium.support.page.PageSupport;

/**
 * 记账
 *
 * @author zeno
 */
@Service
public class UserAccountRecordService {

  @Autowired
  private UserAccountRecordDOMapper userAccountRecordMapper;
  @Autowired
  private UserAccountInfoService userAccountInfoService;

  /**
   * 新增记账
   */
  @Transactional(rollbackFor = Exception.class)
  public Long addRecord(UserAccountRecordDO record) {
    Objects.requireNonNull(record);

    if (record.getTime() == null) {
      record.setTime(new Date());
    }
    updateTime(record);
    userAccountRecordMapper.insertSelective(record);

    // 更新余额
    userAccountInfoService
      .updateBalanceByUid(record.getUid(), record.getAccountType(), record.getType(),
        record.getMoney());

    return record.getId();
  }

  /**
   * 编辑记账
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean editRecord(Long recordId, UserAccountRecordDO record, Long updater) {
    Objects.requireNonNull(recordId);
    Objects.requireNonNull(record);
    Objects.requireNonNull(updater);
    Objects.requireNonNull(record.getAccountType());
    Objects.requireNonNull(record.getType());
    Objects.requireNonNull(record.getMoney());

    UserAccountRecordDO origin = getRecordById(recordId);
    if (origin == null || IsOrNot.True.getKeyByte() == origin.getIsDeleted()) {
      return false;
    }
    if (record.getTime() == null) {
      record.setTime(new Date());
    }
    if (record.getUpdater() == null) {
      record.setUpdater(updater);
    }
    record.setUid(origin.getUid());
    record.setId(recordId);
    updateTime(record);
    int i = userAccountRecordMapper.updateByPrimaryKeySelective(record);
    if (i == 0) {
      return false;
    }
    // 依据原有记录，回滚余额
    if (!revert(origin)) {
      return false;
    }

    // 更新余额
    return userAccountInfoService
      .updateBalanceByUid(record.getUid(), record.getAccountType(), record.getType(),
        record.getMoney());
  }

  /**
   * 删除记账
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean deleteRecord(Long recordId, Long updater) {
    Objects.requireNonNull(recordId);
    Objects.requireNonNull(updater);
    UserAccountRecordDO origin = getRecordById(recordId);
    if (origin == null || IsOrNot.True.getKeyByte() == origin.getIsDeleted()) {
      return true;
    }
    UserAccountRecordDO record = new UserAccountRecordDO();
    record.setId(recordId);
    record.setUpdater(updater);
    record.setIsDeleted(IsOrNot.True.getKeyByte());

    int i = userAccountRecordMapper.updateByPrimaryKeySelective(record);
    if (i == 0) {
      return false;
    }

    // 依据原有记录，回滚余额
    return revert(origin);
  }

  private void updateTime(UserAccountRecordDO record) {
    Objects.requireNonNull(record);
    Objects.requireNonNull(record.getTime());

    LocalDate localDate = LocalDateTime
      .ofInstant(record.getTime().toInstant(), ZoneId.systemDefault()).toLocalDate();
    record.setTimeYear(localDate.getYear());
    record.setTimeMonth(localDate.getMonthValue());
    record.setTimeDay(localDate.getDayOfMonth());
  }

  // 依据原有记录，回滚余额
  private boolean revert(UserAccountRecordDO origin) {
    Objects.requireNonNull(origin);
    Objects.requireNonNull(origin.getUid());
    Objects.requireNonNull(origin.getAccountType());
    Objects.requireNonNull(origin.getType());
    Objects.requireNonNull(origin.getMoney());

    // 反向操作
    byte revertType = AccountRecordType.SPEND.getKeyByte();
    if (AccountRecordType.SPEND.getKeyByte() == origin.getType()) {
      revertType = AccountRecordType.INCOME.getKeyByte();
    }
    return userAccountInfoService
      .updateBalanceByUid(origin.getUid(), origin.getAccountType(), revertType,
        origin.getMoney());
  }

  /**
   * 依据 ID 查询一条记账
   */
  public UserAccountRecordDO getRecordById(Long recordId) {
    Objects.requireNonNull(recordId);

    UserAccountRecordDOExample example = new UserAccountRecordDOExample();
    example.createCriteria()
      .andIdEqualTo(recordId)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    List<UserAccountRecordDO> list = userAccountRecordMapper
      .selectByExample(example);
    if (CollectionUtils.isEmpty(list)) {
      return null;
    }
    return list.get(0);
  }

  /**
   * 分页查询某一批账本的记账记录
   */
  public PageInfo<UserAccountRecordDO> getRecordByAccountBookIdList(Integer pageNumber,
    Integer pageSize, List<Long> accountBookIds) {
    Objects.requireNonNull(accountBookIds);
    if (pageNumber == null || pageNumber <= 0) {
      pageNumber = 1;
    }
    if (pageSize == null || pageSize <= 0) {
      pageSize = 20;
    }
    if (CollectionUtils.isEmpty(accountBookIds)) {
      return PageSupport.transform(Collections.emptyList(), pageNumber, pageSize, 0);
    }

    UserAccountRecordDOExample example = new UserAccountRecordDOExample();
    example.createCriteria()
      .andAccountBookIdIn(accountBookIds)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    example.setOrderByClause(" time DESC");

    return PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(
      () -> userAccountRecordMapper.selectByExample(example)
    );
  }

}
