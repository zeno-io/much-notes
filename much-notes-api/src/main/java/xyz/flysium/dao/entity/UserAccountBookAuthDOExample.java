package xyz.flysium.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAccountBookAuthDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAccountBookAuthDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdIsNull() {
            addCriterion("account_book_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdIsNotNull() {
            addCriterion("account_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdEqualTo(Long value) {
            addCriterion("account_book_id =", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdNotEqualTo(Long value) {
            addCriterion("account_book_id <>", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdGreaterThan(Long value) {
            addCriterion("account_book_id >", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_book_id >=", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdLessThan(Long value) {
            addCriterion("account_book_id <", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdLessThanOrEqualTo(Long value) {
            addCriterion("account_book_id <=", value, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdIn(List<Long> values) {
            addCriterion("account_book_id in", values, "accountBookId");
            return (Criteria) this;
        }

        public Criteria andAccountBookIdNotIn(List<Long> values) {
            addCriterion("account_book_id not in", values, "accountBookId");
            return (Criteria) this;
        }

      public Criteria andAccountBookIdBetween(Long value1, Long value2) {
        addCriterion("account_book_id between", value1, value2, "accountBookId");
        return (Criteria) this;
      }

      public Criteria andAccountBookIdNotBetween(Long value1, Long value2) {
        addCriterion("account_book_id not between", value1, value2, "accountBookId");
        return (Criteria) this;
      }

      public Criteria andUidIsNull() {
        addCriterion("uid is null");
        return (Criteria) this;
      }

      public Criteria andUidIsNotNull() {
        addCriterion("uid is not null");
        return (Criteria) this;
      }

      public Criteria andUidEqualTo(Long value) {
        addCriterion("uid =", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidNotEqualTo(Long value) {
        addCriterion("uid <>", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidGreaterThan(Long value) {
        addCriterion("uid >", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidGreaterThanOrEqualTo(Long value) {
        addCriterion("uid >=", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidLessThan(Long value) {
        addCriterion("uid <", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidLessThanOrEqualTo(Long value) {
        addCriterion("uid <=", value, "uid");
        return (Criteria) this;
      }

      public Criteria andUidIn(List<Long> values) {
        addCriterion("uid in", values, "uid");
        return (Criteria) this;
      }

      public Criteria andUidNotIn(List<Long> values) {
        addCriterion("uid not in", values, "uid");
        return (Criteria) this;
      }

      public Criteria andUidBetween(Long value1, Long value2) {
        addCriterion("uid between", value1, value2, "uid");
        return (Criteria) this;
      }

      public Criteria andUidNotBetween(Long value1, Long value2) {
        addCriterion("uid not between", value1, value2, "uid");
        return (Criteria) this;
      }

      public Criteria andIsAdminIsNull() {
        addCriterion("is_admin is null");
        return (Criteria) this;
      }

      public Criteria andIsAdminIsNotNull() {
        addCriterion("is_admin is not null");
        return (Criteria) this;
      }

        public Criteria andIsAdminEqualTo(Byte value) {
            addCriterion("is_admin =", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotEqualTo(Byte value) {
            addCriterion("is_admin <>", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThan(Byte value) {
            addCriterion("is_admin >", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_admin >=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThan(Byte value) {
            addCriterion("is_admin <", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminLessThanOrEqualTo(Byte value) {
            addCriterion("is_admin <=", value, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminIn(List<Byte> values) {
            addCriterion("is_admin in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotIn(List<Byte> values) {
            addCriterion("is_admin not in", values, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminBetween(Byte value1, Byte value2) {
            addCriterion("is_admin between", value1, value2, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andIsAdminNotBetween(Byte value1, Byte value2) {
            addCriterion("is_admin not between", value1, value2, "isAdmin");
            return (Criteria) this;
        }

        public Criteria andBlockIsNull() {
            addCriterion("block is null");
            return (Criteria) this;
        }

        public Criteria andBlockIsNotNull() {
            addCriterion("block is not null");
            return (Criteria) this;
        }

        public Criteria andBlockEqualTo(Byte value) {
            addCriterion("block =", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockNotEqualTo(Byte value) {
            addCriterion("block <>", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockGreaterThan(Byte value) {
            addCriterion("block >", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockGreaterThanOrEqualTo(Byte value) {
            addCriterion("block >=", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockLessThan(Byte value) {
            addCriterion("block <", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockLessThanOrEqualTo(Byte value) {
            addCriterion("block <=", value, "block");
            return (Criteria) this;
        }

        public Criteria andBlockIn(List<Byte> values) {
            addCriterion("block in", values, "block");
            return (Criteria) this;
        }

        public Criteria andBlockNotIn(List<Byte> values) {
            addCriterion("block not in", values, "block");
            return (Criteria) this;
        }

      public Criteria andBlockBetween(Byte value1, Byte value2) {
        addCriterion("block between", value1, value2, "block");
        return (Criteria) this;
      }

      public Criteria andBlockNotBetween(Byte value1, Byte value2) {
        addCriterion("block not between", value1, value2, "block");
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

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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
