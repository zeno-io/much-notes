package xyz.flysium.dao.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import xyz.flysium.dao.entity.UserAccountInfoDO;
import xyz.flysium.dao.entity.UserAccountInfoDOExample;

@Mapper
public interface UserAccountInfoDOMapper {
    @SelectProvider(type=UserAccountInfoDOSqlProvider.class, method="countByExample")
    long countByExample(UserAccountInfoDOExample example);

    @Insert({
        "insert into user_account_info (uid, account_type, ",
        "balance, create_time, ",
        "update_time, remark)",
        "values (#{uid,jdbcType=BIGINT}, #{accountType,jdbcType=INTEGER}, ",
        "#{balance,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(UserAccountInfoDO record);

    @InsertProvider(type=UserAccountInfoDOSqlProvider.class, method="insertSelective")
    int insertSelective(UserAccountInfoDO record);

    @SelectProvider(type=UserAccountInfoDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="account_type", property="accountType", jdbcType=JdbcType.INTEGER),
        @Result(column="balance", property="balance", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<UserAccountInfoDO> selectByExampleWithRowbounds(UserAccountInfoDOExample example, RowBounds rowBounds);

    @SelectProvider(type=UserAccountInfoDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="account_type", property="accountType", jdbcType=JdbcType.INTEGER),
        @Result(column="balance", property="balance", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<UserAccountInfoDO> selectByExample(UserAccountInfoDOExample example);

    @UpdateProvider(type=UserAccountInfoDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserAccountInfoDO record, @Param("example") UserAccountInfoDOExample example);

    @UpdateProvider(type=UserAccountInfoDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserAccountInfoDO record, @Param("example") UserAccountInfoDOExample example);
}