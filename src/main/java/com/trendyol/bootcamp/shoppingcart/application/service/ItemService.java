package com.trendyol.bootcamp.shoppingcart.application.service;

import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.application.exception.ItemNotFoundException;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.VasItem;
import com.trendyol.bootcamp.shoppingcart.domain.converter.ItemToEntityConverter;
import com.trendyol.bootcamp.shoppingcart.infrastructure.dao.ItemDAO;
import com.trendyol.bootcamp.shoppingcart.infrastructure.entity.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public void addItem(Item item) {
        ItemToEntityConverter converter = new ItemToEntityConverter();
        ItemEntity entity = ItemToEntityConverter.convert(item);
        itemDAO.save(entity);
        System.out.println("Item with ID " + entity.getItemId() + " saved to DB");
    }

    public void removeItem(Long itemId) {
        if (!itemDAO.existsById(itemId)) {
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }
        itemDAO.deleteById(itemId);
        System.out.println("Item with ID " + itemId + " deleted from DB");
    }

    public Item createItem(ItemDto itemDTO){
        Item item;
        if (itemDTO.getCategoryId() == 7889) {
            item = new DigitalItem(itemDTO.getItemId(), itemDTO.getSellerId(), itemDTO.getPrice(),
                    itemDTO.getQuantity());
        } else {
            item = new DefaultItem(itemDTO.getItemId(), itemDTO.getSellerId(), itemDTO.getPrice(),
                    itemDTO.getQuantity(), itemDTO.getCategoryId());
        }
        return item;
    }

    public ItemEntity getItem(Long itemId) {
        if (!itemDAO.existsById(itemId)) {
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }
        ItemEntity entity = itemDAO.findById(itemId).get();
        return entity;
    }
}
