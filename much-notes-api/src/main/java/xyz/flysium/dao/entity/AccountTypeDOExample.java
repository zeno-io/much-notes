package xyz.flysium.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTypeDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountTypeDOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

      public Criteria andNameBetween(String value1, String value2) {
        addCriterion("name between", value1, value2, "name");
        return (Criteria) this;
      }

      public Criteria andNameNotBetween(String value1, String value2) {
        addCriterion("name not between", value1, value2, "name");
        return (Criteria) this;
      }

      public Criteria andCreatorIsNull() {
        addCriterion("creator is null");
        return (Criteria) this;
      }

      public Criteria andCreatorIsNotNull() {
        addCriterion("creator is not null");
        return (Criteria) this;
      }

      public Criteria andCreatorEqualTo(Long value) {
        addCriterion("creator =", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorNotEqualTo(Long value) {
        addCriterion("creator <>", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorGreaterThan(Long value) {
        addCriterion("creator >", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
        addCriterion("creator >=", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorLessThan(Long value) {
        addCriterion("creator <", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorLessThanOrEqualTo(Long value) {
        addCriterion("creator <=", value, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorIn(List<Long> values) {
        addCriterion("creator in", values, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorNotIn(List<Long> values) {
        addCriterion("creator not in", values, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorBetween(Long value1, Long value2) {
        addCriterion("creator between", value1, value2, "creator");
        return (Criteria) this;
      }

      public Criteria andCreatorNotBetween(Long value1, Long value2) {
        addCriterion("creator not between", value1, value2, "creator");
        return (Criteria) this;
      }

      public Criteria andUpdaterIsNull() {
        addCriterion("updater is null");
        return (Criteria) this;
      }

      public Criteria andUpdaterIsNotNull() {
        addCriterion("updater is not null");
        return (Criteria) this;
      }

      public Criteria andUpdaterEqualTo(Long value) {
        addCriterion("updater =", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterNotEqualTo(Long value) {
        addCriterion("updater <>", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterGreaterThan(Long value) {
        addCriterion("updater >", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterGreaterThanOrEqualTo(Long value) {
        addCriterion("updater >=", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterLessThan(Long value) {
        addCriterion("updater <", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterLessThanOrEqualTo(Long value) {
        addCriterion("updater <=", value, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterIn(List<Long> values) {
        addCriterion("updater in", values, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterNotIn(List<Long> values) {
        addCriterion("updater not in", values, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterBetween(Long value1, Long value2) {
        addCriterion("updater between", value1, value2, "updater");
        return (Criteria) this;
      }

      public Criteria andUpdaterNotBetween(Long value1, Long value2) {
        addCriterion("updater not between", value1, value2, "updater");
        return (Criteria) this;
      }

      public Criteria andCreateTimeIsNull() {
        addCriterion("create_time is null");
        return (Criteria) this;
      }

      public Criteria andCreateTimeIsNotNull() {
        addCriterion("create_time is not null");
        return (Criteria) this;
      }

      public Criteria andCreateTimeEqualTo(Date value) {
        addCriterion("create_time =", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeNotEqualTo(Date value) {
        addCriterion("create_time <>", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeGreaterThan(Date value) {
        addCriterion("create_time >", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
        addCriterion("create_time >=", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeLessThan(Date value) {
        addCriterion("create_time <", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
        addCriterion("create_time <=", value, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeIn(List<Date> values) {
        addCriterion("create_time in", values, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeNotIn(List<Date> values) {
        addCriterion("create_time not in", values, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeBetween(Date value1, Date value2) {
        addCriterion("create_time between", value1, value2, "createTime");
        return (Criteria) this;
      }

      public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
        addCriterion("create_time not between", value1, value2, "createTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeIsNull() {
        addCriterion("update_time is null");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeIsNotNull() {
        addCriterion("update_time is not null");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeEqualTo(Date value) {
        addCriterion("update_time =", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeNotEqualTo(Date value) {
        addCriterion("update_time <>", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeGreaterThan(Date value) {
        addCriterion("update_time >", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
        addCriterion("update_time >=", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeLessThan(Date value) {
        addCriterion("update_time <", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
        addCriterion("update_time <=", value, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeIn(List<Date> values) {
        addCriterion("update_time in", values, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeNotIn(List<Date> values) {
        addCriterion("update_time not in", values, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeBetween(Date value1, Date value2) {
        addCriterion("update_time between", value1, value2, "updateTime");
        return (Criteria) this;
      }

      public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
        addCriterion("update_time not between", value1, value2, "updateTime");
        return (Criteria) this;
      }

      public Criteria andRemarkIsNull() {
        addCriterion("remark is null");
        return (Criteria) this;
      }

      public Criteria andRemarkIsNotNull() {
        addCriterion("remark is not null");
        return (Criteria) this;
      }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
