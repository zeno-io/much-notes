package xyz.flysium.dao.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import xyz.flysium.dao.entity.NoteUserDO;
import xyz.flysium.dao.entity.NoteUserDOExample;
import xyz.flysium.dao.entity.NoteUserDOExample.Criteria;
import xyz.flysium.dao.entity.NoteUserDOExample.Criterion;

public class NoteUserDOSqlProvider {
    public String countByExample(NoteUserDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("note_user");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(NoteUserDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("note_user");

        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            sql.VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=CHAR}");
        }

        if (record.getRegisterTime() != null) {
            sql.VALUES("register_time", "#{registerTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRegisterIp() != null) {
            sql.VALUES("register_ip", "#{registerIp,jdbcType=VARCHAR}");
        }

        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }

        if (record.getOpenId() != null) {
            sql.VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }

        if (record.getUnionId() != null) {
            sql.VALUES("union_id", "#{unionId,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }

        if (record.getSessionKey() != null) {
            sql.VALUES("session_key", "#{sessionKey,jdbcType=VARCHAR}");
        }

        if (record.getHeadImg() != null) {
            sql.VALUES("head_img", "#{headImg,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExample(NoteUserDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("username");
        sql.SELECT("nickname");
        sql.SELECT("password");
        sql.SELECT("register_time");
        sql.SELECT("register_ip");
        sql.SELECT("status");
        sql.SELECT("open_id");
        sql.SELECT("union_id");
        sql.SELECT("type");
        sql.SELECT("session_key");
        sql.SELECT("head_img");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("remark");
        sql.FROM("note_user");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        NoteUserDO record = (NoteUserDO) parameter.get("record");
        NoteUserDOExample example = (NoteUserDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("note_user");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }

        if (record.getUsername() != null) {
            sql.SET("username = #{record.username,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            sql.SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{record.password,jdbcType=CHAR}");
        }

        if (record.getRegisterTime() != null) {
            sql.SET("register_time = #{record.registerTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRegisterIp() != null) {
            sql.SET("register_ip = #{record.registerIp,jdbcType=VARCHAR}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }

        if (record.getOpenId() != null) {
            sql.SET("open_id = #{record.openId,jdbcType=VARCHAR}");
        }

        if (record.getUnionId() != null) {
            sql.SET("union_id = #{record.unionId,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=INTEGER}");
        }

        if (record.getSessionKey() != null) {
            sql.SET("session_key = #{record.sessionKey,jdbcType=VARCHAR}");
        }

        if (record.getHeadImg() != null) {
            sql.SET("head_img = #{record.headImg,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("note_user");

        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("username = #{record.username,jdbcType=VARCHAR}");
        sql.SET("nickname = #{record.nickname,jdbcType=VARCHAR}");
        sql.SET("password = #{record.password,jdbcType=CHAR}");
        sql.SET("register_time = #{record.registerTime,jdbcType=TIMESTAMP}");
        sql.SET("register_ip = #{record.registerIp,jdbcType=VARCHAR}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("open_id = #{record.openId,jdbcType=VARCHAR}");
        sql.SET("union_id = #{record.unionId,jdbcType=VARCHAR}");
        sql.SET("type = #{record.type,jdbcType=INTEGER}");
        sql.SET("session_key = #{record.sessionKey,jdbcType=VARCHAR}");
        sql.SET("head_img = #{record.headImg,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");

        NoteUserDOExample example = (NoteUserDOExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(NoteUserDO record) {
        SQL sql = new SQL();
        sql.UPDATE("note_user");

        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }

        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=CHAR}");
        }

        if (record.getRegisterTime() != null) {
            sql.SET("register_time = #{registerTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRegisterIp() != null) {
            sql.SET("register_ip = #{registerIp,jdbcType=VARCHAR}");
        }

        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }

        if (record.getOpenId() != null) {
            sql.SET("open_id = #{openId,jdbcType=VARCHAR}");
        }

        if (record.getUnionId() != null) {
            sql.SET("union_id = #{unionId,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }

        if (record.getSessionKey() != null) {
            sql.SET("session_key = #{sessionKey,jdbcType=VARCHAR}");
        }

        if (record.getHeadImg() != null) {
            sql.SET("head_img = #{headImg,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, NoteUserDOExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
