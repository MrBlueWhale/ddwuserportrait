package com.ibegu.dalaoadmin.domain;

import java.util.ArrayList;
import java.util.List;

public class Rank3TagsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Rank3TagsExample() {
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

        public Criteria andT3IdIsNull() {
            addCriterion("t3_id is null");
            return (Criteria) this;
        }

        public Criteria andT3IdIsNotNull() {
            addCriterion("t3_id is not null");
            return (Criteria) this;
        }

        public Criteria andT3IdEqualTo(Long value) {
            addCriterion("t3_id =", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdNotEqualTo(Long value) {
            addCriterion("t3_id <>", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdGreaterThan(Long value) {
            addCriterion("t3_id >", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdGreaterThanOrEqualTo(Long value) {
            addCriterion("t3_id >=", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdLessThan(Long value) {
            addCriterion("t3_id <", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdLessThanOrEqualTo(Long value) {
            addCriterion("t3_id <=", value, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdIn(List<Long> values) {
            addCriterion("t3_id in", values, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdNotIn(List<Long> values) {
            addCriterion("t3_id not in", values, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdBetween(Long value1, Long value2) {
            addCriterion("t3_id between", value1, value2, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3IdNotBetween(Long value1, Long value2) {
            addCriterion("t3_id not between", value1, value2, "t3Id");
            return (Criteria) this;
        }

        public Criteria andT3NameIsNull() {
            addCriterion("t3_name is null");
            return (Criteria) this;
        }

        public Criteria andT3NameIsNotNull() {
            addCriterion("t3_name is not null");
            return (Criteria) this;
        }

        public Criteria andT3NameEqualTo(String value) {
            addCriterion("t3_name =", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameNotEqualTo(String value) {
            addCriterion("t3_name <>", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameGreaterThan(String value) {
            addCriterion("t3_name >", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameGreaterThanOrEqualTo(String value) {
            addCriterion("t3_name >=", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameLessThan(String value) {
            addCriterion("t3_name <", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameLessThanOrEqualTo(String value) {
            addCriterion("t3_name <=", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameLike(String value) {
            addCriterion("t3_name like", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameNotLike(String value) {
            addCriterion("t3_name not like", value, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameIn(List<String> values) {
            addCriterion("t3_name in", values, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameNotIn(List<String> values) {
            addCriterion("t3_name not in", values, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameBetween(String value1, String value2) {
            addCriterion("t3_name between", value1, value2, "t3Name");
            return (Criteria) this;
        }

        public Criteria andT3NameNotBetween(String value1, String value2) {
            addCriterion("t3_name not between", value1, value2, "t3Name");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNull() {
            addCriterion("process_status is null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNotNull() {
            addCriterion("process_status is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusEqualTo(Integer value) {
            addCriterion("process_status =", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotEqualTo(Integer value) {
            addCriterion("process_status <>", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThan(Integer value) {
            addCriterion("process_status >", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_status >=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThan(Integer value) {
            addCriterion("process_status <", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThanOrEqualTo(Integer value) {
            addCriterion("process_status <=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIn(List<Integer> values) {
            addCriterion("process_status in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotIn(List<Integer> values) {
            addCriterion("process_status not in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusBetween(Integer value1, Integer value2) {
            addCriterion("process_status between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("process_status not between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("`desc` is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("`desc` is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("`desc` =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("`desc` <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("`desc` >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("`desc` >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("`desc` <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("`desc` <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("`desc` like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("`desc` not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("`desc` in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("`desc` not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("`desc` between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("`desc` not between", value1, value2, "desc");
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