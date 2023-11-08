package com.trendyol.bootcamp.shoppingcart.domain.converter;

import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.infrastructure.entity.ItemEntity;

public class ItemToEntityConverter {

    public static ItemEntity convert(Item item) {
        ItemEntity entity = new ItemEntity();
        entity.setItemId(item.getId());
        entity.setPrice(item.getPrice());
        entity.setCategoryId(item.getCategoryId());
        entity.setQuantity(item.getQuantity());
        entity.setSellerId(item.getSellerId());
        return entity;
    }
}
