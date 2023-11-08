package com.trendyol.bootcamp.shoppingcart.api.dto;


import java.util.List;

public class CartDto {
    private List<ItemDto> items;
    private double totalPrice;
    private int appliedPromotionId;
    private double totalDiscount;

    public CartDto(List<ItemDto> items, double totalPrice, int appliedPromotionId, double totalDiscount) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.appliedPromotionId = appliedPromotionId;
        this.totalDiscount = totalDiscount;
    }

    public CartDto(){}

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAppliedPromotionId() {
        return appliedPromotionId;
    }

    public void setAppliedPromotionId(int appliedPromotionId) {
        this.appliedPromotionId = appliedPromotionId;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
