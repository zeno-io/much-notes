package xyz.flysium.service;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.flysium.constant.enums.AccountRecordType;
import xyz.flysium.dao.entity.UserAccountInfoDO;
import xyz.flysium.dao.entity.UserAccountInfoDOExample;
import xyz.flysium.dao.repository.UserAccountInfoDOMapper;
import xyz.flysium.support.mybatis.QuerySupport;

/**
 * 资产账户管理
 *
 * @author zeno
 */
@Service
public class UserAccountInfoService {

  @Autowired
  private UserAccountInfoDOMapper accountInfoMapper;

  /**
   * 更新资产信息
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean updateBalanceByUid(Long uid, Integer accountType, Byte recordType,
    Long money) {
    Objects.requireNonNull(uid);
    Objects.requireNonNull(accountType);
    Objects.requireNonNull(recordType);
    Objects.requireNonNull(money);

    UserAccountInfoDOExample example = new UserAccountInfoDOExample();
    example.createCriteria()
      .andUidEqualTo(uid)
      .andAccountTypeEqualTo(accountType);
    List<UserAccountInfoDO> list = accountInfoMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(list)) {
      return false;
    }
    UserAccountInfoDO accountInfo = list.get(0);

    UserAccountInfoDO record = new UserAccountInfoDO();
    if (AccountRecordType.SPEND.getKeyByte() == recordType) {
      record.setBalance(accountInfo.getBalance() - money);
    } else {
      record.setBalance(accountInfo.getBalance() + money);
    }

    return accountInfoMapper.updateByExampleSelective(record, example) > 0;
  }

  /**
   * 编辑资产信息
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean directUpdateBalanceByUid(Long uid, Long updater, Integer accountType,
    Long balance) {
    Objects.requireNonNull(uid);
    Objects.requireNonNull(updater);
    Objects.requireNonNull(accountType);
    Objects.requireNonNull(balance);

    UserAccountInfoDO record = new UserAccountInfoDO();
    record.setUpdater(updater);
    record.setBalance(balance);

    UserAccountInfoDOExample example = new UserAccountInfoDOExample();
    example.createCriteria()
      .andUidEqualTo(uid)
      .andAccountTypeEqualTo(accountType);

    return accountInfoMapper.updateByExampleSelective(record, example) > 0;
  }

  /**
   * 获取资产信息
   */
  public List<UserAccountInfoDO> queryBalancesByUidList(List<Long> uidList) {
    UserAccountInfoDOExample example = new UserAccountInfoDOExample();
    example.createCriteria()
      .andUidIn(uidList);
    return QuerySupport.queryAll((rowBounds ->
      accountInfoMapper.selectByExampleWithRowbounds(example, rowBounds)
    ));
  }

}
