package com.trendyol.bootcamp.shoppingcart.domain.model.Item;

import java.util.List;

public abstract class Item {
    private int MAX_QUANTITY_IN_CART;
    private Long id;

    private String title;

    private Long categoryId;

    private double price;

    private int quantity;

    private Long sellerId;

    private int numberInCart;

    private List<VasItem> vasItem;

    public Item(Long itemId, Long sellerId, double price, int quantity, Long categoryId, int MAX_QUANTITY_IN_CART) {
        this.id = itemId;
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.MAX_QUANTITY_IN_CART = MAX_QUANTITY_IN_CART;
    }

    // GETTERS AND SETTERS

    public int getMAX_QUANTITY_IN_CART() {
        return MAX_QUANTITY_IN_CART;
    }

    public void setMAX_QUANTITY_IN_CART(int MAX_QUANTITY_IN_CART) {
        this.MAX_QUANTITY_IN_CART = MAX_QUANTITY_IN_CART;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public List<VasItem> getVasItems() {
        return vasItem;
    }

    public void setVasItem(List<VasItem> vasItems) {
        this.vasItem = vasItems;
    }

}
