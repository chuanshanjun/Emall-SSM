
package com.dayuanit.emall.vo;

public class CartVO {
    private int goodId;

    private int goodCounts;

    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(int goodCounts) {
        this.goodCounts = goodCounts;
    }
}
