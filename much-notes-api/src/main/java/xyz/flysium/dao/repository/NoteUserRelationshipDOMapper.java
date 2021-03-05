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
import xyz.flysium.dao.entity.NoteUserRelationshipDO;
import xyz.flysium.dao.entity.NoteUserRelationshipDOExample;

@Mapper
public interface NoteUserRelationshipDOMapper {
    @SelectProvider(type=NoteUserRelationshipDOSqlProvider.class, method="countByExample")
    long countByExample(NoteUserRelationshipDOExample example);

    @Insert({
        "insert into note_user_rel (uid, that_uid, ",
        "rel_type, creator, ",
        "updater, create_time, ",
        "update_time, remark, ",
        "is_deleted)",
        "values (#{uid,jdbcType=BIGINT}, #{thatUid,jdbcType=BIGINT}, ",
        "#{relType,jdbcType=TINYINT}, #{creator,jdbcType=BIGINT}, ",
        "#{updater,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR}, ",
        "#{isDeleted,jdbcType=TINYINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(NoteUserRelationshipDO record);

    @InsertProvider(type=NoteUserRelationshipDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(NoteUserRelationshipDO record);

    @SelectProvider(type=NoteUserRelationshipDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="that_uid", property="thatUid", jdbcType=JdbcType.BIGINT),
        @Result(column="rel_type", property="relType", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<NoteUserRelationshipDO> selectByExampleWithRowbounds(NoteUserRelationshipDOExample example, RowBounds rowBounds);

    @SelectProvider(type=NoteUserRelationshipDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="that_uid", property="thatUid", jdbcType=JdbcType.BIGINT),
        @Result(column="rel_type", property="relType", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    List<NoteUserRelationshipDO> selectByExample(NoteUserRelationshipDOExample example);

    @Select({
        "select",
        "id, uid, that_uid, rel_type, creator, updater, create_time, update_time, remark, ",
        "is_deleted",
        "from note_user_rel",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.BIGINT),
        @Result(column="that_uid", property="thatUid", jdbcType=JdbcType.BIGINT),
        @Result(column="rel_type", property="relType", jdbcType=JdbcType.TINYINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="updater", property="updater", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_deleted", property="isDeleted", jdbcType=JdbcType.TINYINT)
    })
    NoteUserRelationshipDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=NoteUserRelationshipDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") NoteUserRelationshipDO record, @Param("example") NoteUserRelationshipDOExample example);

    @UpdateProvider(type=NoteUserRelationshipDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") NoteUserRelationshipDO record, @Param("example") NoteUserRelationshipDOExample example);

    @UpdateProvider(type=NoteUserRelationshipDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NoteUserRelationshipDO record);

    @Update({
        "update note_user_rel",
        "set uid = #{uid,jdbcType=BIGINT},",
          "that_uid = #{thatUid,jdbcType=BIGINT},",
          "rel_type = #{relType,jdbcType=TINYINT},",
          "creator = #{creator,jdbcType=BIGINT},",
          "updater = #{updater,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_deleted = #{isDeleted,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NoteUserRelationshipDO record);
}