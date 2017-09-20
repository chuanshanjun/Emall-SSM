package com.dayuanit.emall.pojo;

import java.util.Date;

public class MallAddress {
    private Integer id;

    private Byte userId;

    private String province;

    private String city;

    private String area;

    private String accurateAddress;

    private String realName;

    private Byte cellphoneNum;

    private Byte status;

    private Byte defaultAddress;

    private String createTime;

    private Date modifyTime;

    private String provinceWord;

    private String cityWord;

    private String areaWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getUserId() {
        return userId;
    }

    public void setUserId(Byte userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAccurateAddress() {
        return accurateAddress;
    }

    public void setAccurateAddress(String accurateAddress) {
        this.accurateAddress = accurateAddress == null ? null : accurateAddress.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getCellphoneNum() {
        return cellphoneNum;
    }

    public void setCellphoneNum(Byte cellphoneNum) {
        this.cellphoneNum = cellphoneNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Byte defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getProvinceWord() {
        return provinceWord;
    }

    public void setProvinceWord(String provinceWord) {
        this.provinceWord = provinceWord == null ? null : provinceWord.trim();
    }

    public String getCityWord() {
        return cityWord;
    }

    public void setCityWord(String cityWord) {
        this.cityWord = cityWord == null ? null : cityWord.trim();
    }

    public String getAreaWord() {
        return areaWord;
    }

    public void setAreaWord(String areaWord) {
        this.areaWord = areaWord == null ? null : areaWord.trim();
    }
}