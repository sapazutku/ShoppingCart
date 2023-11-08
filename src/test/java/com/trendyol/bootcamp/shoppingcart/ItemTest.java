package com.trendyol.bootcamp.shoppingcart;


import com.trendyol.bootcamp.shoppingcart.application.exception.ItemQuantityException;
import com.trendyol.bootcamp.shoppingcart.application.service.CartService;
import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.VasItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemTest {

    @Autowired
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void should_Have_5_Digital_Items_In_Cart(){

        Item digitalItem1 = new DigitalItem(123L, 12L, 3, 5);
        Item digitalItem2 = new DigitalItem(123L, 12L, 3, 1);

        cart.addItem(digitalItem1);

        assertThrows(ItemQuantityException.class, () -> cart.addItem(digitalItem2));
    }

    @Test
    void should_Not_Allow_Adding_Default_Item_When_Digital_Items_Exist() {

        DigitalItem digitalItem = new DigitalItem(123L, 12L, 3, 5);
        DefaultItem defaultItem = new DefaultItem(2L, 12L, 3, 5, 3003L);

        cart.addItem(digitalItem);

        assertThrows(RuntimeException.class, () -> cart.addItem(defaultItem));
    }

    @Test
    void should_Not_Allow_Adding_Digital_Item_When_Default_Items_Exist() {

        DigitalItem digitalItem = new DigitalItem(123L, 12L, 3, 5);
        DefaultItem defaultItem = new DefaultItem(2L, 12L, 3, 5, 3003L);

        cart.addItem(defaultItem);

        assertThrows(RuntimeException.class, () -> cart.addItem(digitalItem));
    }

    @Test
    void should_Add_VasItem_To_DefaultItem() {
        DefaultItem defaultItem = new DefaultItem(123L, 12L, 3, 1, 1001L);
        VasItem vasItem = new VasItem(1001L, 3242L, 3L, 10, 3);

        cartService.addItemToCart(defaultItem);
        cartService.addVasItemToDefaultItem(defaultItem.getId(), vasItem);

        assertEquals(1, defaultItem.getVasItems().size());
    }

    @Test
    void should_Not_Allow_Add_VasItem_To_DigitalItem() {
        DigitalItem digitalItem = new DigitalItem(123L, 12L, 3, 1);
        VasItem vasItem = new VasItem(1001L, 3242L, 3L, 10, 3);

        cart.addItem(digitalItem);

        assertThrows(RuntimeException.class, () -> cartService.addVasItemToDefaultItem(digitalItem.getId(), vasItem));
    }

}
