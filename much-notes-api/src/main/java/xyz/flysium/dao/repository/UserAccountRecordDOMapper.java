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
import xyz.flysium.dao.entity.UserAccountRecordDO;
import xyz.flysium.dao.entity.UserAccountRecordDOExample;

@Mapper
public interface UserAccountRecordDOMapper {
    @SelectProvider(type=UserAccountRecordDOSqlProvider.class, method="countByExample")
    long countByExample(UserAccountRecordDOExample example);

    @Insert({
      "insert into user_account_record (money, type, ",
      "account_type, account_book_id, ",
      "uid, time, time_year, ",
      "time_month, time_day, ",
      "category_name, category_id, ",
      "creator, updater, ",
      "create_time, update_time, ",
      "remark, is_deleted)",
      "values (#{money,jdbcType=BIGINT}, #{type,jdbcType=TINYINT}, ",
      "#{accountType,jdbcType=INTEGER}, #{accountBookId,jdbcType=BIGINT}, ",
      "#{uid,jdbcType=BIGINT}, #{time,jdbcType=DATE}, #{timeYear,jdbcType=INTEGER}, ",
      "#{timeMonth,jdbcType=INTEGER}, #{timeDay,jdbcType=INTEGER}, ",
      "#{categoryName,jdbcType=VARCHAR}, #{categoryId,jdbcType=BIGINT}, ",
      "#{creator,jdbcType=BIGINT}, #{updater,jdbcType=BIGINT}, ",
      "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
      "#{remark,jdbcType=VARCHAR}, #{isDeleted,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountRecordDO record);

    @InsertProvider(type=UserAccountRecordDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountRecordDO record);

    @SelectProvider(type=UserAccountRecordDOSqlProvider.class, method="selectByExample")
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "money", property = "money", jdbcType = JdbcType.BIGINT),
      @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
      @Result(column = "account_type", property = "accountType", jdbcType = JdbcType.INTEGER),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "time", property = "time", jdbcType = JdbcType.DATE),
      @Result(column = "time_year", property = "timeYear", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_month", property = "timeMonth", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_day", property = "timeDay", jdbcType = JdbcType.INTEGER),
      @Result(column = "category_name", property = "categoryName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    List<UserAccountRecordDO> selectByExampleWithRowbounds(UserAccountRecordDOExample example, RowBounds rowBounds);

    @SelectProvider(type=UserAccountRecordDOSqlProvider.class, method="selectByExample")
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "money", property = "money", jdbcType = JdbcType.BIGINT),
      @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
      @Result(column = "account_type", property = "accountType", jdbcType = JdbcType.INTEGER),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "time", property = "time", jdbcType = JdbcType.DATE),
      @Result(column = "time_year", property = "timeYear", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_month", property = "timeMonth", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_day", property = "timeDay", jdbcType = JdbcType.INTEGER),
      @Result(column = "category_name", property = "categoryName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    List<UserAccountRecordDO> selectByExample(UserAccountRecordDOExample example);

    @Select({
      "select",
      "id, money, type, account_type, account_book_id, uid, time, time_year, time_month, ",
      "time_day, category_name, category_id, creator, updater, create_time, update_time, ",
      "remark, is_deleted",
      "from user_account_record",
      "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
      @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
      @Result(column = "money", property = "money", jdbcType = JdbcType.BIGINT),
      @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
      @Result(column = "account_type", property = "accountType", jdbcType = JdbcType.INTEGER),
      @Result(column = "account_book_id", property = "accountBookId", jdbcType = JdbcType.BIGINT),
      @Result(column = "uid", property = "uid", jdbcType = JdbcType.BIGINT),
      @Result(column = "time", property = "time", jdbcType = JdbcType.DATE),
      @Result(column = "time_year", property = "timeYear", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_month", property = "timeMonth", jdbcType = JdbcType.INTEGER),
      @Result(column = "time_day", property = "timeDay", jdbcType = JdbcType.INTEGER),
      @Result(column = "category_name", property = "categoryName", jdbcType = JdbcType.VARCHAR),
      @Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
      @Result(column = "creator", property = "creator", jdbcType = JdbcType.BIGINT),
      @Result(column = "updater", property = "updater", jdbcType = JdbcType.BIGINT),
      @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
      @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
      @Result(column = "is_deleted", property = "isDeleted", jdbcType = JdbcType.TINYINT)
    })
    UserAccountRecordDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountRecordDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountRecordDO record, @Param("example") UserAccountRecordDOExample example);

    @UpdateProvider(type=UserAccountRecordDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountRecordDO record, @Param("example") UserAccountRecordDOExample example);

    @UpdateProvider(type=UserAccountRecordDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountRecordDO record);

    @Update({
      "update user_account_record",
      "set money = #{money,jdbcType=BIGINT},",
      "type = #{type,jdbcType=TINYINT},",
      "account_type = #{accountType,jdbcType=INTEGER},",
      "account_book_id = #{accountBookId,jdbcType=BIGINT},",
      "uid = #{uid,jdbcType=BIGINT},",
      "time = #{time,jdbcType=DATE},",
      "time_year = #{timeYear,jdbcType=INTEGER},",
      "time_month = #{timeMonth,jdbcType=INTEGER},",
      "time_day = #{timeDay,jdbcType=INTEGER},",
      "category_name = #{categoryName,jdbcType=VARCHAR},",
      "category_id = #{categoryId,jdbcType=BIGINT},",
      "creator = #{creator,jdbcType=BIGINT},",
      "updater = #{updater,jdbcType=BIGINT},",
      "create_time = #{createTime,jdbcType=TIMESTAMP},",
      "update_time = #{updateTime,jdbcType=TIMESTAMP},",
      "remark = #{remark,jdbcType=VARCHAR},",
      "is_deleted = #{isDeleted,jdbcType=TINYINT}",
      "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountRecordDO record);
}
