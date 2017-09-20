package com.dayuanit.emall.dto;

import com.dayuanit.emall.pojo.MallGoods;

import java.util.List;

public class GoodsOrderInfoDTO {

    private String amount;
    private String freight;
    private String preferentialAmount;
    private List<GoodsInfo> goods;

    public List<GoodsInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInfo> goods) {
        this.goods = goods;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(String preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public static class GoodsInfo {
        private String goodName;
        private String amount;
        private int counts;

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }
    }
}
