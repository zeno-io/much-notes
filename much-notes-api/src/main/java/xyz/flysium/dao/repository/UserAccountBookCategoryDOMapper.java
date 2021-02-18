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
import xyz.flysium.dao.entity.UserAccountBookCategoryDO;
import xyz.flysium.dao.entity.UserAccountBookCategoryDOExample;

@Mapper
public interface UserAccountBookCategoryDOMapper {
    @SelectProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="countByExample")
    long countByExample(UserAccountBookCategoryDOExample example);

    @Insert({
        "insert into user_account_book_category (uid, gid, ",
        "cid, create_time, ",
        "update_time, remark)",
        "values (#{uid,jdbcType=BIGINT}, #{gid,jdbcType=BIGINT}, ",
        "#{cid,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserAccountBookCategoryDO record);

    @InsertProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UserAccountBookCategoryDO record);

    @SelectProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="gid", property="gid", jdbcType=JdbcType.BIGINT),
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<UserAccountBookCategoryDO> selectByExampleWithRowbounds(UserAccountBookCategoryDOExample example, RowBounds rowBounds);

    @SelectProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="gid", property="gid", jdbcType=JdbcType.BIGINT),
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<UserAccountBookCategoryDO> selectByExample(UserAccountBookCategoryDOExample example);

    @Select({
        "select",
        "id, uid, gid, cid, create_time, update_time, remark",
        "from user_account_book_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="gid", property="gid", jdbcType=JdbcType.BIGINT),
        @Result(column="cid", property="cid", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    UserAccountBookCategoryDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountBookCategoryDO record, @Param("example") UserAccountBookCategoryDOExample example);

    @UpdateProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountBookCategoryDO record, @Param("example") UserAccountBookCategoryDOExample example);

    @UpdateProvider(type=UserAccountBookCategoryDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAccountBookCategoryDO record);

    @Update({
        "update user_account_book_category",
        "set uid = #{uid,jdbcType=BIGINT},",
          "gid = #{gid,jdbcType=BIGINT},",
          "cid = #{cid,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserAccountBookCategoryDO record);
}