package com.trendyol.bootcamp.shoppingcart.application.service;

import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.application.exception.ItemNotFoundException;
import com.trendyol.bootcamp.shoppingcart.application.exception.VasItemException;
import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.VasItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    Cart cart;

    public DefaultItem addVasItemToDefaultItem(Long defaultItemId, VasItem vasItem) {
        // check if default item is in cart
        System.out.println("DEFAULT ITEM ID: " + defaultItemId);
        if (cart.findById(defaultItemId) != null) {
            DefaultItem item = cart.findDefaultItemById(defaultItemId);
            item.addVasItem(vasItem);
            System.out.println("VasItem added to DefaultItem");
            return item;
        }
        else{
            throw new ItemNotFoundException("DefaultItem couldn't found in the Cart");
        }
    }

    public boolean removeItem(Long itemId) {
        if (cart.findById(itemId) != null) {
            cart.removeItem(cart.findById(itemId));
            return true;
        }
        else {
            return false;
        }
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

    public void addItemToCart(Item item) {
        cart.addItem(item);
    }

    public void calculatePromotions(){

    }

    public void resetCart() {
        cart.resetCart();
    }

    public Cart getCart() {
        return cart;
    }
}
