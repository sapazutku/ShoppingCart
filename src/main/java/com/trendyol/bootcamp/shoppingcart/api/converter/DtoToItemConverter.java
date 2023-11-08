package com.trendyol.bootcamp.shoppingcart.api.converter;

import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;

public class DtoToItemConverter {
    public Item convertDtoToItem(ItemDto itemDto) {
        return new DefaultItem(itemDto.getItemId(),itemDto.getSellerId(), itemDto.getPrice(), itemDto.getQuantity(),
                itemDto.getCategoryId());
    }
}
