package xyz.flysium.dao.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import xyz.flysium.dao.entity.UserAccountRecordDO;
import xyz.flysium.dao.entity.UserAccountRecordDOExample;
import xyz.flysium.dao.entity.UserAccountRecordDOExample.Criteria;
import xyz.flysium.dao.entity.UserAccountRecordDOExample.Criterion;

public class UserAccountRecordDOSqlProvider {
    public String countByExample(UserAccountRecordDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("user_account_record");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(UserAccountRecordDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_account_record");

        if (record.getMoney() != null) {
            sql.VALUES("money", "#{money,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=TINYINT}");
        }

      if (record.getAccountType() != null) {
        sql.VALUES("account_type", "#{accountType,jdbcType=INTEGER}");
      }

      if (record.getAccountBookId() != null) {
        sql.VALUES("account_book_id", "#{accountBookId,jdbcType=BIGINT}");
      }

      if (record.getUid() != null) {
        sql.VALUES("uid", "#{uid,jdbcType=BIGINT}");
      }

      if (record.getTime() != null) {
        sql.VALUES("time", "#{time,jdbcType=DATE}");
      }

      if (record.getTimeYear() != null) {
        sql.VALUES("time_year", "#{timeYear,jdbcType=INTEGER}");
      }

      if (record.getTimeMonth() != null) {
            sql.VALUES("time_month", "#{timeMonth,jdbcType=INTEGER}");
        }

        if (record.getTimeDay() != null) {
            sql.VALUES("time_day", "#{timeDay,jdbcType=INTEGER}");
        }

      if (record.getCategoryName() != null) {
        sql.VALUES("category_name", "#{categoryName,jdbcType=VARCHAR}");
      }

      if (record.getCategoryId() != null) {
        sql.VALUES("category_id", "#{categoryId,jdbcType=BIGINT}");
      }

      if (record.getCreator() != null) {
        sql.VALUES("creator", "#{creator,jdbcType=BIGINT}");
      }

      if (record.getUpdater() != null) {
        sql.VALUES("updater", "#{updater,jdbcType=BIGINT}");
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

        if (record.getIsDeleted() != null) {
            sql.VALUES("is_deleted", "#{isDeleted,jdbcType=TINYINT}");
        }

        return sql.toString();
    }

    public String selectByExample(UserAccountRecordDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("money");
      sql.SELECT("type");
      sql.SELECT("account_type");
      sql.SELECT("account_book_id");
      sql.SELECT("uid");
      sql.SELECT("time");
      sql.SELECT("time_year");
      sql.SELECT("time_month");
      sql.SELECT("time_day");
      sql.SELECT("category_name");
      sql.SELECT("category_id");
      sql.SELECT("creator");
      sql.SELECT("updater");
      sql.SELECT("create_time");
      sql.SELECT("update_time");
      sql.SELECT("remark");
      sql.SELECT("is_deleted");
      sql.FROM("user_account_record");
      applyWhere(sql, example, false);

      if (example != null && example.getOrderByClause() != null) {
        sql.ORDER_BY(example.getOrderByClause());
      }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        UserAccountRecordDO record = (UserAccountRecordDO) parameter.get("record");
        UserAccountRecordDOExample example = (UserAccountRecordDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("user_account_record");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }

        if (record.getMoney() != null) {
            sql.SET("money = #{record.money,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=TINYINT}");
        }

      if (record.getAccountType() != null) {
        sql.SET("account_type = #{record.accountType,jdbcType=INTEGER}");
      }

      if (record.getAccountBookId() != null) {
        sql.SET("account_book_id = #{record.accountBookId,jdbcType=BIGINT}");
      }

      if (record.getUid() != null) {
        sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
      }

      if (record.getTime() != null) {
        sql.SET("time = #{record.time,jdbcType=DATE}");
      }

      if (record.getTimeYear() != null) {
        sql.SET("time_year = #{record.timeYear,jdbcType=INTEGER}");
      }

      if (record.getTimeMonth() != null) {
            sql.SET("time_month = #{record.timeMonth,jdbcType=INTEGER}");
        }

        if (record.getTimeDay() != null) {
            sql.SET("time_day = #{record.timeDay,jdbcType=INTEGER}");
        }

      if (record.getCategoryName() != null) {
        sql.SET("category_name = #{record.categoryName,jdbcType=VARCHAR}");
      }

      if (record.getCategoryId() != null) {
        sql.SET("category_id = #{record.categoryId,jdbcType=BIGINT}");
      }

      if (record.getCreator() != null) {
        sql.SET("creator = #{record.creator,jdbcType=BIGINT}");
      }

      if (record.getUpdater() != null) {
        sql.SET("updater = #{record.updater,jdbcType=BIGINT}");
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

        if (record.getIsDeleted() != null) {
            sql.SET("is_deleted = #{record.isDeleted,jdbcType=TINYINT}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("user_account_record");

        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("money = #{record.money,jdbcType=BIGINT}");
      sql.SET("type = #{record.type,jdbcType=TINYINT}");
      sql.SET("account_type = #{record.accountType,jdbcType=INTEGER}");
      sql.SET("account_book_id = #{record.accountBookId,jdbcType=BIGINT}");
      sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
      sql.SET("time = #{record.time,jdbcType=DATE}");
      sql.SET("time_year = #{record.timeYear,jdbcType=INTEGER}");
      sql.SET("time_month = #{record.timeMonth,jdbcType=INTEGER}");
      sql.SET("time_day = #{record.timeDay,jdbcType=INTEGER}");
      sql.SET("category_name = #{record.categoryName,jdbcType=VARCHAR}");
      sql.SET("category_id = #{record.categoryId,jdbcType=BIGINT}");
      sql.SET("creator = #{record.creator,jdbcType=BIGINT}");
      sql.SET("updater = #{record.updater,jdbcType=BIGINT}");
      sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
      sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
      sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");
      sql.SET("is_deleted = #{record.isDeleted,jdbcType=TINYINT}");

      UserAccountRecordDOExample example = (UserAccountRecordDOExample) parameter.get("example");
      applyWhere(sql, example, true);
      return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserAccountRecordDO record) {
        SQL sql = new SQL();
        sql.UPDATE("user_account_record");

        if (record.getMoney() != null) {
            sql.SET("money = #{money,jdbcType=BIGINT}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=TINYINT}");
        }

      if (record.getAccountType() != null) {
        sql.SET("account_type = #{accountType,jdbcType=INTEGER}");
      }

      if (record.getAccountBookId() != null) {
        sql.SET("account_book_id = #{accountBookId,jdbcType=BIGINT}");
      }

      if (record.getUid() != null) {
        sql.SET("uid = #{uid,jdbcType=BIGINT}");
      }

      if (record.getTime() != null) {
        sql.SET("time = #{time,jdbcType=DATE}");
      }

      if (record.getTimeYear() != null) {
        sql.SET("time_year = #{timeYear,jdbcType=INTEGER}");
      }

      if (record.getTimeMonth() != null) {
            sql.SET("time_month = #{timeMonth,jdbcType=INTEGER}");
        }

        if (record.getTimeDay() != null) {
            sql.SET("time_day = #{timeDay,jdbcType=INTEGER}");
        }

      if (record.getCategoryName() != null) {
        sql.SET("category_name = #{categoryName,jdbcType=VARCHAR}");
      }

      if (record.getCategoryId() != null) {
        sql.SET("category_id = #{categoryId,jdbcType=BIGINT}");
      }

      if (record.getCreator() != null) {
        sql.SET("creator = #{creator,jdbcType=BIGINT}");
      }

      if (record.getUpdater() != null) {
        sql.SET("updater = #{updater,jdbcType=BIGINT}");
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

        if (record.getIsDeleted() != null) {
            sql.SET("is_deleted = #{isDeleted,jdbcType=TINYINT}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, UserAccountRecordDOExample example, boolean includeExamplePhrase) {
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
