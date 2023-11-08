package com.trendyol.bootcamp.shoppingcart.api.dto;

import com.trendyol.bootcamp.shoppingcart.domain.model.Item.VasItem;

import java.util.List;

public class ItemDto {
    private Long itemId;
    private Long categoryId;
    private Long sellerId;
    private Double price;
    private Integer quantity;

    private List<VasItem> vasItems;

    public ItemDto(Long itemId, Long categoryId, Long sellerId, Double price, Integer quantity, List<VasItem> vasItems) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.vasItems = vasItems;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<VasItem> getVasItems() {
        return vasItems;
    }

    public void setVasItems(List<VasItem> vasItems) {
        this.vasItems = vasItems;
    }
}