package com.trendyol.bootcamp.shoppingcart.domain.model.Item;

public class DigitalItem extends Item {

    private static final int MAX_QUANTITY_IN_CART = 5;
    private static final Long CATEGORY_ID = 7889L;

    public DigitalItem(Long itemId, Long sellerId, double price, int quantity) {
        super(itemId, sellerId, price, quantity, CATEGORY_ID, MAX_QUANTITY_IN_CART);

    }
}
