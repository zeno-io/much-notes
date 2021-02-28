package xyz.flysium.dao.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import xyz.flysium.dao.entity.UserAccountInfoDO;
import xyz.flysium.dao.entity.UserAccountInfoDOExample;
import xyz.flysium.dao.entity.UserAccountInfoDOExample.Criteria;
import xyz.flysium.dao.entity.UserAccountInfoDOExample.Criterion;

public class UserAccountInfoDOSqlProvider {
    public String countByExample(UserAccountInfoDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("user_account_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(UserAccountInfoDO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_account_info");

        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }

      if (record.getAccountType() != null) {
        sql.VALUES("account_type", "#{accountType,jdbcType=INTEGER}");
      }

      if (record.getBalance() != null) {
        sql.VALUES("balance", "#{balance,jdbcType=BIGINT}");
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

        return sql.toString();
    }

    public String selectByExample(UserAccountInfoDOExample example) {
      SQL sql = new SQL();
      if (example != null && example.isDistinct()) {
        sql.SELECT_DISTINCT("uid");
      } else {
        sql.SELECT("uid");
      }
      sql.SELECT("account_type");
      sql.SELECT("balance");
      sql.SELECT("creator");
      sql.SELECT("updater");
      sql.SELECT("create_time");
      sql.SELECT("update_time");
      sql.SELECT("remark");
      sql.FROM("user_account_info");
      applyWhere(sql, example, false);

      if (example != null && example.getOrderByClause() != null) {
        sql.ORDER_BY(example.getOrderByClause());
      }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        UserAccountInfoDO record = (UserAccountInfoDO) parameter.get("record");
        UserAccountInfoDOExample example = (UserAccountInfoDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("user_account_info");

        if (record.getUid() != null) {
            sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
        }

      if (record.getAccountType() != null) {
        sql.SET("account_type = #{record.accountType,jdbcType=INTEGER}");
      }

      if (record.getBalance() != null) {
        sql.SET("balance = #{record.balance,jdbcType=BIGINT}");
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

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
      SQL sql = new SQL();
      sql.UPDATE("user_account_info");

      sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
      sql.SET("account_type = #{record.accountType,jdbcType=INTEGER}");
      sql.SET("balance = #{record.balance,jdbcType=BIGINT}");
      sql.SET("creator = #{record.creator,jdbcType=BIGINT}");
      sql.SET("updater = #{record.updater,jdbcType=BIGINT}");
      sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
      sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
      sql.SET("remark = #{record.remark,jdbcType=VARCHAR}");

      UserAccountInfoDOExample example = (UserAccountInfoDOExample) parameter.get("example");
      applyWhere(sql, example, true);
      return sql.toString();
    }

    protected void applyWhere(SQL sql, UserAccountInfoDOExample example, boolean includeExamplePhrase) {
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
