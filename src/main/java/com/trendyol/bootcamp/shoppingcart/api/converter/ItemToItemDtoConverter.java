package com.trendyol.bootcamp.shoppingcart.api.converter;

import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemToItemDtoConverter {
    public List<ItemDto> convert(List<Item> items) {
        return items.stream().map(item -> new ItemDto(item.getId(), item.getCategoryId(), item.getSellerId(),
                item.getPrice(), item.getQuantity(), item.getVasItems())).collect(Collectors.toList());

    }
}
