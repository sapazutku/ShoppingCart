package com.trendyol.bootcamp.shoppingcart.api.controller;

import com.trendyol.bootcamp.shoppingcart.api.converter.DtoToItemConverter;
import com.trendyol.bootcamp.shoppingcart.api.dto.ItemDto;
import com.trendyol.bootcamp.shoppingcart.application.service.ItemService;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addItem(@RequestBody ItemDto itemDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            DtoToItemConverter converter = new DtoToItemConverter();
            Item item = converter.convertDtoToItem(itemDto);
            itemService.addItem(item);
            response.put("result", true);
            response.put("message", "Item added successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable Long itemId) {
        Map<String, Object> response = new HashMap<>();
        try {
            itemService.removeItem(itemId);
            response.put("result", true);
            response.put("message", "Item deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
