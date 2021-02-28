package xyz.flysium.dao.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import xyz.flysium.dao.entity.UserAccountBookAuthDO;
import xyz.flysium.dao.entity.UserAccountBookAuthDOExample;

@Mapper
public interface UserAccountBookAuthDOMapper {
    @SelectProvider(type=UserAccountBookAuthDOSqlProvider.class, method="countByExample")
    long countByExample(UserAccountBookAuthDOExample example);

    @Insert({
      "insert into user_account_book_auth (account_book_id, uid, ",
      "is_admin, block, ",
      "creator, updater, ",
      "create_time, update_time, ",
      "remark, is_deleted)",
      "values (#{accountBookId,jdbcType=BIGINT}, #{uid,jdbcType=BIGINT}, ",
      "#{isAdmin,jdbcType=TINYINT}, #{block,jdbcType=TINYINT}, ",
      "#{creator,jdbcType=BIGINT}, #{updater,jdbcType=BIGINT}, ",
      "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
      "#{remark,jdbcType=VARCHAR}, #{isDeleted,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountBookAuthDO record);

    @InsertProvider(type=UserAccountBookAuthDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountBookAuthDO record);

    @SelectProvider(type=UserAccountBookAuthDOSqlProvider.class, method="selectByExample")
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "is_admin", property = "isAdmin", jdbcType = JdbcType.TINYINT),
      @Result(column = "block", property = "block", jdbcType = JdbcType.TINYINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    List<UserAccountBookAuthDO> selectByExampleWithRowbounds(UserAccountBookAuthDOExample example, RowBounds rowBounds);

    @SelectProvider(type=UserAccountBookAuthDOSqlProvider.class, method="selectByExample")
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "is_admin", property = "isAdmin", jdbcType = JdbcType.TINYINT),
      @Result(column = "block", property = "block", jdbcType = JdbcType.TINYINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    List<UserAccountBookAuthDO> selectByExample(UserAccountBookAuthDOExample example);

    @Select({
      "select",
      "id, account_book_id, uid, is_admin, block, creator, updater, create_time, update_time, ",
      "remark, is_deleted",
      "from user_account_book_auth",
      "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "is_admin", property = "isAdmin", jdbcType = JdbcType.TINYINT),
      @Result(column = "block", property = "block", jdbcType = JdbcType.TINYINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    UserAccountBookAuthDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountBookAuthDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountBookAuthDO record, @Param("example") UserAccountBookAuthDOExample example);

    @UpdateProvider(type=UserAccountBookAuthDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountBookAuthDO record, @Param("example") UserAccountBookAuthDOExample example);

    @UpdateProvider(type=UserAccountBookAuthDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountBookAuthDO record);

    @Update({
      "update user_account_book_auth",
      "set account_book_id = #{accountBookId,jdbcType=BIGINT},",
      "uid = #{uid,jdbcType=BIGINT},",
      "is_admin = #{isAdmin,jdbcType=TINYINT},",
      "block = #{block,jdbcType=TINYINT},",
      "creator = #{creator,jdbcType=BIGINT},",
      "updater = #{updater,jdbcType=BIGINT},",
      "create_time = #{createTime,jdbcType=TIMESTAMP},",
      "update_time = #{updateTime,jdbcType=TIMESTAMP},",
      "remark = #{remark,jdbcType=VARCHAR},",
      "is_deleted = #{isDeleted,jdbcType=TINYINT}",
      "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountBookAuthDO record);
}
