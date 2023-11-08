package com.trendyol.bootcamp.shoppingcart.domain.model.Item;

import com.trendyol.bootcamp.shoppingcart.api.dto.VasItemDto;

public class VasItem {

    private Long id ;
    private  Long categoryId;

    private Long sellerId;

    private double price;

    private int quantity;


    public VasItem(Long id, Long categoryId, Long sellerId, double price, int quantity) {
        this.id = id;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;

    }

    public VasItem(VasItemDto vasItemDto) {
        this.id = vasItemDto.getItemId();
        this.price = vasItemDto.getPrice();
        this.categoryId = (long) vasItemDto.getCategoryId();
        this.sellerId = (long) vasItemDto.getSellerId();
        this.quantity = vasItemDto.getQuantity();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
