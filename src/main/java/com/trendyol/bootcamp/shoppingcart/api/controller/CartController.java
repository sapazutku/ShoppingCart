package com.trendyol.bootcamp.shoppingcart.api.controller;

import com.trendyol.bootcamp.shoppingcart.api.converter.ItemToItemDtoConverter;
import com.trendyol.bootcamp.shoppingcart.api.dto.CartDto;
import com.trendyol.bootcamp.shoppingcart.api.dto.VasItemDto;
import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.api.dto.RemoveItemDto;
import com.trendyol.bootcamp.shoppingcart.application.service.CartService;
import com.trendyol.bootcamp.shoppingcart.application.service.ItemService;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.VasItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @PostMapping("/add/item")
    public ResponseEntity<Map<String, Object>> addItemToCart(@RequestBody ItemDto itemDTO) {
        Map<String, Object> response = new HashMap<>();
        Item item = itemService.createItem(itemDTO);
        try{
            cartService.addItemToCart(item);
            response.put("result", true);
            response.put("message", "Item added successfully!");
            System.out.println("Item added successfully!");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("add/vasitem")
    public ResponseEntity<Map<String, Object>> addVasItemToDefaultItem(@RequestBody VasItemDto vasItemDto) {
        Map<String, Object> response = new HashMap<>();
        if (vasItemDto.getCategoryId() == 3242) {
            VasItem vasItem = new VasItem(vasItemDto);
            System.out.println("DEFAULT ITEM ID: " + vasItemDto.getItemId());
            cartService.addVasItemToDefaultItem((long)vasItemDto.getItemId(), vasItem);
            response.put("result", true);
            response.put("message", "Vas Item added successfully!");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("result", false);
            response.put("message", "Vas Item Id is not valid");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/remove/item")
    public ResponseEntity<Map<String, Object>> removeItemFromCart(@RequestBody RemoveItemDto removeItemDTO) {
        Map<String, Object> response = new HashMap<>();
        if (cartService.removeItem(removeItemDTO.getItemId())) {
            response.put("result", true);
            response.put("message", "Item removed successfully!");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("result", false);
            response.put("message", "Item not found!");
            return ResponseEntity.notFound().build();
        }
    }

    // reset cart
    @DeleteMapping("/reset")
    public ResponseEntity<Map<String, Object>> resetCart() {
        Map<String, Object> response = new HashMap<>();
        cartService.resetCart();
        response.put("result", true);
        response.put("message", "Cart reset successfully!");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart() {
        Map<String, Object> response = new HashMap<>();
        ItemToItemDtoConverter itemToItemDtoConverter = new ItemToItemDtoConverter();
        CartDto cartDto = new CartDto(
                itemToItemDtoConverter.convert(cartService.getCart().getItems()),
                cartService.getCart().calculateTotalAmountAfterDiscount(),
                cartService.getCart().findBestPromotion().getId().intValue(),
                cartService.getCart().calculateTotalAmountWithoutDiscount() - cartService.getCart().calculateTotalAmountAfterDiscount()
        );

        response.put("result", true);
        response.put("message", cartDto);
        return ResponseEntity.ok(response);
    }
}
