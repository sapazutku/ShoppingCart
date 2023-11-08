package com.trendyol.bootcamp.shoppingcart.domain.model.Item;

import com.trendyol.bootcamp.shoppingcart.application.exception.VasItemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultItem extends Item {
    private static final int MAX_QUANTITY_IN_CART = 10;
    private static final int MAX_VASITEM = 3;
    private static final List<Long> vasItemsCategoryIds = Arrays.asList(1001L, 3004L);
    private List<VasItem> vasItems = new ArrayList<>();

    public DefaultItem(Long itemId, Long sellerId, double price, int quantity, Long categoryId) {
        super(itemId, sellerId, price, quantity, categoryId, MAX_QUANTITY_IN_CART);
    }


    public void addVasItem(VasItem vasItem) {
        if (vasItemsCategoryIds.contains(this.getCategoryId())) {
            if (vasItems.stream().mapToInt(VasItem::getQuantity).sum() + vasItem.getQuantity() <= MAX_VASITEM) {
                this.vasItems.add(vasItem);
            } else {
                throw new VasItemException("You can add max " + MAX_VASITEM + " vas item to this item");
            }
        } else {
            throw new IllegalArgumentException("VasItem can only be added to categoriesIds " + vasItemsCategoryIds);
        }
    }

    public List<VasItem> getVasItems() {
        return vasItems;
    }



    public double calculateVasItemsPrice() {
        double vasItemsPrice = 0;
        for (VasItem vasItem : vasItems) {
            vasItemsPrice += vasItem.getPrice() * vasItem.getQuantity();
        }
        return vasItemsPrice;
    }
}