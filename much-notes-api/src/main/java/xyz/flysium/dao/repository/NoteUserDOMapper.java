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
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.NoteUserDOExample;

@Mapper
public interface NoteUserDOMapper {
    @SelectProvider(type=NoteUserDOSqlProvider.class, method="countByExample")
    long countByExample(NoteUserDOExample example);

    @Insert({
        "insert into note_user (username, nickname, ",
        "password, register_time, ",
        "register_ip, status, ",
        "open_id, union_id, ",
        "type, session_key, ",
        "head_img, create_time, ",
        "update_time, remark)",
        "values (#{username,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=CHAR}, #{registerTime,jdbcType=TIMESTAMP}, ",
        "#{registerIp,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{openId,jdbcType=VARCHAR}, #{unionId,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{sessionKey,jdbcType=VARCHAR}, ",
        "#{headImg,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(NoteUserDO record);

    @InsertProvider(type=NoteUserDOSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(NoteUserDO record);

    @SelectProvider(type=NoteUserDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="register_ip", property="registerIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="session_key", property="sessionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_img", property="headImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<NoteUserDO> selectByExampleWithRowbounds(NoteUserDOExample example, RowBounds rowBounds);

    @SelectProvider(type=NoteUserDOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="register_ip", property="registerIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="session_key", property="sessionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_img", property="headImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<NoteUserDO> selectByExample(NoteUserDOExample example);

    @Select({
        "select",
        "id, username, nickname, password, register_time, register_ip, status, open_id, ",
        "union_id, type, session_key, head_img, create_time, update_time, remark",
        "from note_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.CHAR),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="register_ip", property="registerIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="session_key", property="sessionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_img", property="headImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    NoteUserDO selectByPrimaryKey(Long id);

    @UpdateProvider(type=NoteUserDOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") NoteUserDO record, @Param("example") NoteUserDOExample example);

    @UpdateProvider(type=NoteUserDOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") NoteUserDO record, @Param("example") NoteUserDOExample example);

    @UpdateProvider(type=NoteUserDOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NoteUserDO record);

    @Update({
        "update note_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=CHAR},",
          "register_time = #{registerTime,jdbcType=TIMESTAMP},",
          "register_ip = #{registerIp,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "open_id = #{openId,jdbcType=VARCHAR},",
          "union_id = #{unionId,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "session_key = #{sessionKey,jdbcType=VARCHAR},",
          "head_img = #{headImg,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(NoteUserDO record);
}