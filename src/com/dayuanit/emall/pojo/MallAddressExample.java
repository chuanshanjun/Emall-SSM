package com.dayuanit.emall.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallAddressExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Byte value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Byte value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Byte value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Byte value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Byte value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Byte> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Byte> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Byte value1, Byte value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Byte value1, Byte value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressIsNull() {
            addCriterion("accurate_address is null");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressIsNotNull() {
            addCriterion("accurate_address is not null");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressEqualTo(String value) {
            addCriterion("accurate_address =", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressNotEqualTo(String value) {
            addCriterion("accurate_address <>", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressGreaterThan(String value) {
            addCriterion("accurate_address >", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressGreaterThanOrEqualTo(String value) {
            addCriterion("accurate_address >=", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressLessThan(String value) {
            addCriterion("accurate_address <", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressLessThanOrEqualTo(String value) {
            addCriterion("accurate_address <=", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressLike(String value) {
            addCriterion("accurate_address like", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressNotLike(String value) {
            addCriterion("accurate_address not like", value, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressIn(List<String> values) {
            addCriterion("accurate_address in", values, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressNotIn(List<String> values) {
            addCriterion("accurate_address not in", values, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressBetween(String value1, String value2) {
            addCriterion("accurate_address between", value1, value2, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andAccurateAddressNotBetween(String value1, String value2) {
            addCriterion("accurate_address not between", value1, value2, "accurateAddress");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumIsNull() {
            addCriterion("cellphone_num is null");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumIsNotNull() {
            addCriterion("cellphone_num is not null");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumEqualTo(Byte value) {
            addCriterion("cellphone_num =", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumNotEqualTo(Byte value) {
            addCriterion("cellphone_num <>", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumGreaterThan(Byte value) {
            addCriterion("cellphone_num >", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("cellphone_num >=", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumLessThan(Byte value) {
            addCriterion("cellphone_num <", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumLessThanOrEqualTo(Byte value) {
            addCriterion("cellphone_num <=", value, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumIn(List<Byte> values) {
            addCriterion("cellphone_num in", values, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumNotIn(List<Byte> values) {
            addCriterion("cellphone_num not in", values, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumBetween(Byte value1, Byte value2) {
            addCriterion("cellphone_num between", value1, value2, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andCellphoneNumNotBetween(Byte value1, Byte value2) {
            addCriterion("cellphone_num not between", value1, value2, "cellphoneNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressIsNull() {
            addCriterion("default_address is null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressIsNotNull() {
            addCriterion("default_address is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressEqualTo(Byte value) {
            addCriterion("default_address =", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressNotEqualTo(Byte value) {
            addCriterion("default_address <>", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressGreaterThan(Byte value) {
            addCriterion("default_address >", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressGreaterThanOrEqualTo(Byte value) {
            addCriterion("default_address >=", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressLessThan(Byte value) {
            addCriterion("default_address <", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressLessThanOrEqualTo(Byte value) {
            addCriterion("default_address <=", value, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressIn(List<Byte> values) {
            addCriterion("default_address in", values, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressNotIn(List<Byte> values) {
            addCriterion("default_address not in", values, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressBetween(Byte value1, Byte value2) {
            addCriterion("default_address between", value1, value2, "defaultAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultAddressNotBetween(Byte value1, Byte value2) {
            addCriterion("default_address not between", value1, value2, "defaultAddress");
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

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andProvinceWordIsNull() {
            addCriterion("province_word is null");
            return (Criteria) this;
        }

        public Criteria andProvinceWordIsNotNull() {
            addCriterion("province_word is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceWordEqualTo(String value) {
            addCriterion("province_word =", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordNotEqualTo(String value) {
            addCriterion("province_word <>", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordGreaterThan(String value) {
            addCriterion("province_word >", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordGreaterThanOrEqualTo(String value) {
            addCriterion("province_word >=", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordLessThan(String value) {
            addCriterion("province_word <", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordLessThanOrEqualTo(String value) {
            addCriterion("province_word <=", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordLike(String value) {
            addCriterion("province_word like", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordNotLike(String value) {
            addCriterion("province_word not like", value, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordIn(List<String> values) {
            addCriterion("province_word in", values, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordNotIn(List<String> values) {
            addCriterion("province_word not in", values, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordBetween(String value1, String value2) {
            addCriterion("province_word between", value1, value2, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andProvinceWordNotBetween(String value1, String value2) {
            addCriterion("province_word not between", value1, value2, "provinceWord");
            return (Criteria) this;
        }

        public Criteria andCityWordIsNull() {
            addCriterion("city_word is null");
            return (Criteria) this;
        }

        public Criteria andCityWordIsNotNull() {
            addCriterion("city_word is not null");
            return (Criteria) this;
        }

        public Criteria andCityWordEqualTo(String value) {
            addCriterion("city_word =", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordNotEqualTo(String value) {
            addCriterion("city_word <>", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordGreaterThan(String value) {
            addCriterion("city_word >", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordGreaterThanOrEqualTo(String value) {
            addCriterion("city_word >=", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordLessThan(String value) {
            addCriterion("city_word <", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordLessThanOrEqualTo(String value) {
            addCriterion("city_word <=", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordLike(String value) {
            addCriterion("city_word like", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordNotLike(String value) {
            addCriterion("city_word not like", value, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordIn(List<String> values) {
            addCriterion("city_word in", values, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordNotIn(List<String> values) {
            addCriterion("city_word not in", values, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordBetween(String value1, String value2) {
            addCriterion("city_word between", value1, value2, "cityWord");
            return (Criteria) this;
        }

        public Criteria andCityWordNotBetween(String value1, String value2) {
            addCriterion("city_word not between", value1, value2, "cityWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordIsNull() {
            addCriterion("area_word is null");
            return (Criteria) this;
        }

        public Criteria andAreaWordIsNotNull() {
            addCriterion("area_word is not null");
            return (Criteria) this;
        }

        public Criteria andAreaWordEqualTo(String value) {
            addCriterion("area_word =", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordNotEqualTo(String value) {
            addCriterion("area_word <>", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordGreaterThan(String value) {
            addCriterion("area_word >", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordGreaterThanOrEqualTo(String value) {
            addCriterion("area_word >=", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordLessThan(String value) {
            addCriterion("area_word <", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordLessThanOrEqualTo(String value) {
            addCriterion("area_word <=", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordLike(String value) {
            addCriterion("area_word like", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordNotLike(String value) {
            addCriterion("area_word not like", value, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordIn(List<String> values) {
            addCriterion("area_word in", values, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordNotIn(List<String> values) {
            addCriterion("area_word not in", values, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordBetween(String value1, String value2) {
            addCriterion("area_word between", value1, value2, "areaWord");
            return (Criteria) this;
        }

        public Criteria andAreaWordNotBetween(String value1, String value2) {
            addCriterion("area_word not between", value1, value2, "areaWord");
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