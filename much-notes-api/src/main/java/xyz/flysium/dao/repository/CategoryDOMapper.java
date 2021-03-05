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
import xyz.flysium.dao.entity.CategoryDO;
import xyz.flysium.dao.entity.CategoryDOExample;

@Mapper
public interface CategoryDOMapper {
    @SelectProvider(type=CategoryDOSqlProvider.class, method="countByExample")
    long countByExample(CategoryDOExample example);

    @Insert({
        "insert into category (name, icon, ",
        "is_custom, type, ",
        "creator, updater, ",
        "create_time, update_time, ",
        "remark, is_deleted)",
        "values (#{name,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, ",
        "#{isCustom,jdbcType=TINYINT}, #{type,jdbcType=TINYINT}, ",
        "#{creator,jdbcType=BIGINT}, #{updater,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR}, #{isDeleted,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CategoryDO record);

    @InsertProvider(type=CategoryDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(CategoryDO record);

    @SelectProvider(type=CategoryDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_custom", property="isCustom", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<CategoryDO> selectByExampleWithRowbounds(CategoryDOExample example, RowBounds rowBounds);

    @SelectProvider(type=CategoryDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_custom", property="isCustom", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<CategoryDO> selectByExample(CategoryDOExample example);

    @Select({
        "select",
        "id, name, icon, is_custom, type, creator, updater, create_time, update_time, ",
        "remark, is_deleted",
        "from category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_custom", property="isCustom", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    CategoryDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=CategoryDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CategoryDO record, @Param("example") CategoryDOExample example);

    @UpdateProvider(type=CategoryDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CategoryDO record, @Param("example") CategoryDOExample example);

    @UpdateProvider(type=CategoryDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CategoryDO record);

    @Update({
        "update category",
        "set name = #{name,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "is_custom = #{isCustom,jdbcType=TINYINT},",
          "type = #{type,jdbcType=TINYINT},",
          "creator = #{creator,jdbcType=BIGINT},",
          "updater = #{updater,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_deleted = #{isDeleted,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CategoryDO record);
}