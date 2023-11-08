package com.trendyol.bootcamp.shoppingcart.domain.model.Promotion;

import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;


public abstract class Promotion {
    protected Long id;
    protected double discountRatio;
    protected double discountAmount;


    public abstract double calculateDiscount(Cart cart);

    public abstract boolean isApplicable(Cart cart);




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

}
