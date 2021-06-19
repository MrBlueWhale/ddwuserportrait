package com.ibegu.dalaoadmin.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupTagExample() {
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

        public Criteria andGtIdIsNull() {
            addCriterion("gt_id is null");
            return (Criteria) this;
        }

        public Criteria andGtIdIsNotNull() {
            addCriterion("gt_id is not null");
            return (Criteria) this;
        }

        public Criteria andGtIdEqualTo(Long value) {
            addCriterion("gt_id =", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdNotEqualTo(Long value) {
            addCriterion("gt_id <>", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdGreaterThan(Long value) {
            addCriterion("gt_id >", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdGreaterThanOrEqualTo(Long value) {
            addCriterion("gt_id >=", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdLessThan(Long value) {
            addCriterion("gt_id <", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdLessThanOrEqualTo(Long value) {
            addCriterion("gt_id <=", value, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdIn(List<Long> values) {
            addCriterion("gt_id in", values, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdNotIn(List<Long> values) {
            addCriterion("gt_id not in", values, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdBetween(Long value1, Long value2) {
            addCriterion("gt_id between", value1, value2, "gtId");
            return (Criteria) this;
        }

        public Criteria andGtIdNotBetween(Long value1, Long value2) {
            addCriterion("gt_id not between", value1, value2, "gtId");
            return (Criteria) this;
        }

        public Criteria andBt5IdIsNull() {
            addCriterion("bt5_id is null");
            return (Criteria) this;
        }

        public Criteria andBt5IdIsNotNull() {
            addCriterion("bt5_id is not null");
            return (Criteria) this;
        }

        public Criteria andBt5IdEqualTo(Long value) {
            addCriterion("bt5_id =", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdNotEqualTo(Long value) {
            addCriterion("bt5_id <>", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdGreaterThan(Long value) {
            addCriterion("bt5_id >", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdGreaterThanOrEqualTo(Long value) {
            addCriterion("bt5_id >=", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdLessThan(Long value) {
            addCriterion("bt5_id <", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdLessThanOrEqualTo(Long value) {
            addCriterion("bt5_id <=", value, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdIn(List<Long> values) {
            addCriterion("bt5_id in", values, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdNotIn(List<Long> values) {
            addCriterion("bt5_id not in", values, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdBetween(Long value1, Long value2) {
            addCriterion("bt5_id between", value1, value2, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5IdNotBetween(Long value1, Long value2) {
            addCriterion("bt5_id not between", value1, value2, "bt5Id");
            return (Criteria) this;
        }

        public Criteria andBt5NameIsNull() {
            addCriterion("bt5_name is null");
            return (Criteria) this;
        }

        public Criteria andBt5NameIsNotNull() {
            addCriterion("bt5_name is not null");
            return (Criteria) this;
        }

        public Criteria andBt5NameEqualTo(String value) {
            addCriterion("bt5_name =", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameNotEqualTo(String value) {
            addCriterion("bt5_name <>", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameGreaterThan(String value) {
            addCriterion("bt5_name >", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameGreaterThanOrEqualTo(String value) {
            addCriterion("bt5_name >=", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameLessThan(String value) {
            addCriterion("bt5_name <", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameLessThanOrEqualTo(String value) {
            addCriterion("bt5_name <=", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameLike(String value) {
            addCriterion("bt5_name like", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameNotLike(String value) {
            addCriterion("bt5_name not like", value, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameIn(List<String> values) {
            addCriterion("bt5_name in", values, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameNotIn(List<String> values) {
            addCriterion("bt5_name not in", values, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameBetween(String value1, String value2) {
            addCriterion("bt5_name between", value1, value2, "bt5Name");
            return (Criteria) this;
        }

        public Criteria andBt5NameNotBetween(String value1, String value2) {
            addCriterion("bt5_name not between", value1, value2, "bt5Name");
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

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
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