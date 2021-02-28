package xyz.flysium.service;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.flysium.constant.enums.UserStatus;
import xyz.flysium.dao.entity.AccountTypeDO;
import xyz.flysium.dao.entity.AccountTypeDOExample;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.NoteUserDOExample;
import xyz.flysium.dao.entity.UserAccountInfoDO;
import xyz.flysium.dao.repository.AccountTypeDOMapper;
import xyz.flysium.dao.repository.NoteUserDOMapper;
import xyz.flysium.dao.repository.UserAccountInfoDOMapper;
import xyz.flysium.support.mybatis.QuerySupport;

/**
 * 用户管理
 *
 * @author zeno
 */
@Service
public class UserService {

  @Autowired
  private NoteUserDOMapper userMapper;
  @Autowired
  private AccountTypeDOMapper accountTypeMapper;
  @Autowired
  private UserAccountInfoDOMapper userAccountInfoMapper;

  public NoteUserDO getUnFilterUserById(Long uid) {
    Objects.requireNonNull(uid);
    return userMapper.selectByPrimaryKey(uid);
  }

  public NoteUserDO getUserById(Long uid) {
    Objects.requireNonNull(uid);
    NoteUserDOExample example = new NoteUserDOExample();
    example.createCriteria()
      .andIdEqualTo(uid)
      .andStatusEqualTo(UserStatus.VALID.getKey());
    List<NoteUserDO> list = userMapper.selectByExample(example);
    if (CollectionUtils.isNotEmpty(list)) {
      return list.get(0);
    }
    return null;
  }

  public List<NoteUserDO> getUsersByIds(List<Long> uidList) {
    Objects.requireNonNull(uidList);
    NoteUserDOExample example = new NoteUserDOExample();
    example.createCriteria()
      .andIdIn(uidList)
      .andStatusEqualTo(UserStatus.VALID.getKey());
    return QuerySupport.queryAll((rowBounds -> {
      return userMapper.selectByExampleWithRowbounds(example, rowBounds);
    }));
  }

  // https://mp.weixin.qq.com/wxamp/basicprofile/relation?token=1232573385&lang=zh_CN
  public NoteUserDO getUnFilterUserByUnionId(String unionId) {
    Objects.requireNonNull(unionId);

    NoteUserDOExample example = new NoteUserDOExample();
    example.createCriteria()
      .andUnionIdEqualTo(unionId);
    List<NoteUserDO> list = userMapper.selectByExample(example);
    if (CollectionUtils.isNotEmpty(list)) {
      return list.get(0);
    }
    return null;
  }

  @Transactional(rollbackFor = Exception.class)
  public NoteUserDO addUser(NoteUserDO user) {
    Objects.requireNonNull(user);
    Objects.requireNonNull(user.getUnionId());

    NoteUserDO originUser = getUnFilterUserByUnionId(user.getUnionId());
    if (originUser != null) {
      return originUser;
    }
    // 没有查询到用户就创建用户
    userMapper.insertSelective(user);

    AccountTypeDOExample example = new AccountTypeDOExample();
    List<AccountTypeDO> accountTypes = QuerySupport.queryAll((rowBounds -> {
      return accountTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
    }));
    // 插入余额数据
    for (AccountTypeDO accountType : accountTypes) {
      UserAccountInfoDO userAccountInfo = new UserAccountInfoDO();
      userAccountInfo.setAccountType(accountType.getType());
      userAccountInfo.setBalance(0L);
      userAccountInfo.setUid(user.getId());
      userAccountInfo.setCreator(user.getId());
      userAccountInfoMapper.insertSelective(userAccountInfo);
    }

    return user;
  }

}
