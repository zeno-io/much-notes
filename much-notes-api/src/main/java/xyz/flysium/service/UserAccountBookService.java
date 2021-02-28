package xyz.flysium.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.flysium.constant.enums.AccountBookType;
import xyz.flysium.constant.enums.IsOrNot;
import xyz.flysium.dao.entity.UserAccountBookAuthDO;
import xyz.flysium.dao.entity.UserAccountBookAuthDOExample;
import xyz.flysium.dao.entity.UserAccountBookDO;
import xyz.flysium.dao.entity.UserAccountBookDOExample;
import xyz.flysium.dao.repository.UserAccountBookAuthDOMapper;
import xyz.flysium.dao.repository.UserAccountBookDOMapper;
import xyz.flysium.support.mybatis.QuerySupport;

/**
 * 账本管理
 *
 * @author zeno
 */
@Service
public class UserAccountBookService {

  @Autowired
  private UserAccountBookDOMapper userAccountBookMapper;
  @Autowired
  private UserAccountBookAuthDOMapper userAccountBookAuthMapper;

  /**
   * 创建账本
   */
  @Transactional(rollbackFor = Exception.class)
  public Long addNormalAccountBook(String name, Long uid) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(uid);

    UserAccountBookDO userAccountBook = new UserAccountBookDO();
    userAccountBook.setName(name);
    userAccountBook.setType(AccountBookType.NORMAL.getKeyByte());
    userAccountBook.setUid(uid);
    userAccountBookMapper.insertSelective(userAccountBook);

    UserAccountBookAuthDO userAccountBookAuth = new UserAccountBookAuthDO();
    userAccountBookAuth.setUid(uid);
    userAccountBookAuth.setAccountBookId(userAccountBook.getId());
    userAccountBookAuth.setIsAdmin(IsOrNot.True.getKeyByte());
    userAccountBookAuth.setIsDeleted(IsOrNot.False.getKeyByte());
    userAccountBookAuthMapper.insertSelective(userAccountBookAuth);

    return userAccountBook.getId();
  }

  /**
   * 将用户添加到账本
   */
  @Transactional(rollbackFor = Exception.class)
  public void addUserToAccountBook(Long accountBookId, Long uid) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(uid);

    UserAccountBookAuthDO userAccountBookAuth = new UserAccountBookAuthDO();
    userAccountBookAuth.setUid(uid);
    userAccountBookAuth.setAccountBookId(accountBookId);
    userAccountBookAuth.setIsAdmin(IsOrNot.False.getKeyByte());
    userAccountBookAuth.setIsDeleted(IsOrNot.False.getKeyByte());
    userAccountBookAuthMapper.insertSelective(userAccountBookAuth);
  }

  /**
   * 将用户从账本移除
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean removeUserFromAccountBook(Long accountBookId, Long uid) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(uid);

    UserAccountBookAuthDO record = new UserAccountBookAuthDO();
    record.setIsDeleted(IsOrNot.True.getKeyByte());

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andUidEqualTo(uid)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    return userAccountBookAuthMapper.updateByExampleSelective(record, example) > 0;
  }

  /**
   * 移交管理员
   */
  @Transactional(rollbackFor = Exception.class)
  public boolean changeAdmin(@NotBlank Long accountBookId, Long uid, Long thatUid) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(uid);
    Objects.requireNonNull(thatUid);

    UserAccountBookAuthDO record = new UserAccountBookAuthDO();
    record.setIsAdmin(IsOrNot.False.getKeyByte());

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andUidEqualTo(uid)
      .andIsAdminEqualTo(IsOrNot.True.getKeyByte())
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    int i = userAccountBookAuthMapper.updateByExampleSelective(record, example);
    if (i == 0) {
      return false;
    }
    record = new UserAccountBookAuthDO();
    record.setIsAdmin(IsOrNot.True.getKeyByte());

    example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andUidEqualTo(thatUid)
      .andIsAdminEqualTo(IsOrNot.False.getKeyByte())
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    i = userAccountBookAuthMapper.updateByExampleSelective(record, example);
    return i != 0;
  }

  /**
   * 获取单个账本信息
   */
  public UserAccountBookDO getAccountBookById(Long accountBookId) {
    Objects.requireNonNull(accountBookId);

    return userAccountBookMapper.selectByPrimaryKey(accountBookId);
  }

  /**
   * 统计单个账本的用户数
   */
  public long countAccountBookById(Long accountBookId) {
    Objects.requireNonNull(accountBookId);

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    return userAccountBookAuthMapper.countByExample(example);
  }

  /**
   * 获取用户下的所有账本信息
   */
  public List<UserAccountBookDO> queryAccountBookListByUid(Long uid) {
    Objects.requireNonNull(uid);

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andUidEqualTo(uid)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    List<UserAccountBookAuthDO> userAccountBookAuths = QuerySupport.queryAll((rowBounds -> {
      return userAccountBookAuthMapper.selectByExampleWithRowbounds(example, rowBounds);
    }));

    if (CollectionUtils.isEmpty(userAccountBookAuths)) {
      return Collections.emptyList();
    }
    UserAccountBookDOExample bookExample = new UserAccountBookDOExample();
    bookExample.createCriteria()
      .andIdIn(userAccountBookAuths.stream().map(UserAccountBookAuthDO::getAccountBookId)
        .collect(Collectors.toList()));
    bookExample.setOrderByClause(" update_time DESC ");
    return QuerySupport.queryAll((rowBounds -> {
      return userAccountBookMapper.selectByExampleWithRowbounds(bookExample, rowBounds);
    }));
  }

  /**
   * 获取账本下的所有用户列表
   */
  public List<UserAccountBookAuthDO> queryAccountBookUsersById(Long accountBookId) {
    Objects.requireNonNull(accountBookId);

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());

    return QuerySupport.queryAll((rowBounds -> {
      return userAccountBookAuthMapper.selectByExampleWithRowbounds(example, rowBounds);
    }));
  }


  /**
   * 查询指定用户的所有账本关系
   */
  public List<UserAccountBookAuthDO> queryAccountBookUsersByUid(Long uid) {
    Objects.requireNonNull(uid);

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andUidEqualTo(uid)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    return userAccountBookAuthMapper
      .selectByExample(example);
  }

  /**
   * 查询指定用户的指定账本关系
   */
  public UserAccountBookAuthDO getAccountBookUser(Long accountBookId, Long uid) {
    Objects.requireNonNull(accountBookId);
    Objects.requireNonNull(uid);

    UserAccountBookAuthDOExample example = new UserAccountBookAuthDOExample();
    example.createCriteria()
      .andAccountBookIdEqualTo(accountBookId)
      .andUidEqualTo(uid)
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    List<UserAccountBookAuthDO> list = userAccountBookAuthMapper
      .selectByExample(example);
    return list.stream().findFirst().orElse(null);
  }


}

