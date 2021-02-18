package xyz.flysium.dao.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import xyz.flysium.dao.entity.AccountTypeDO;
import xyz.flysium.dao.entity.AccountTypeDOExample;

@Mapper
public interface AccountTypeDOMapper {
    @SelectProvider(type=AccountTypeDOSqlProvider.class, method="countByExample")
    long countByExample(AccountTypeDOExample example);

    @Insert({
        "insert into account_type (type, name, ",
        "remark)",
        "values (#{type,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(AccountTypeDO record);

    @InsertProvider(type=AccountTypeDOSqlProvider.class, method="insertSelective")
    int insertSelective(AccountTypeDO record);

    @SelectProvider(type=AccountTypeDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<AccountTypeDO> selectByExampleWithRowbounds(AccountTypeDOExample example, RowBounds rowBounds);

    @SelectProvider(type=AccountTypeDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<AccountTypeDO> selectByExample(AccountTypeDOExample example);

    @Select({
        "select",
        "type, name, remark",
        "from account_type",
        "where type = #{type,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    AccountTypeDO selectByPrimaryKey(Integer type);

    @UpdateProvider(type=AccountTypeDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AccountTypeDO record, @Param("example") AccountTypeDOExample example);

    @UpdateProvider(type=AccountTypeDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AccountTypeDO record, @Param("example") AccountTypeDOExample example);

    @UpdateProvider(type=AccountTypeDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccountTypeDO record);

    @Update({
        "update account_type",
        "set name = #{name,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where type = #{type,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AccountTypeDO record);
}