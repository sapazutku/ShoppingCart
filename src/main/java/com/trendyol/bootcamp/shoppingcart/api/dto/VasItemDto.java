package com.trendyol.bootcamp.shoppingcart.api.dto;

public class VasItemDto {
    private Long itemId;
    private int vasItemId;
    private int categoryId;
    private int sellerId;
    private double price;
    private int quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getVasItemId() {
        return vasItemId;
    }

    public void setVasItemId(int vasItemId) {
        this.vasItemId = vasItemId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
