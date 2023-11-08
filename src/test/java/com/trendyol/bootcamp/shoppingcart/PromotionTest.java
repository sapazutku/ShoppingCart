package com.trendyol.bootcamp.shoppingcart;

import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PromotionTest {

    Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }


    @Test
    void shoud_Apply_SameSellerPromotion(){
        /*
           Same Seller -> %10 ,   Toplam tutar: 10000 , İndirim: 1000
        * */
        DefaultItem defaultItem1 = new DefaultItem(123L, 12L, 9000, 1, 3003L);
        DefaultItem defaultItem2 = new DefaultItem(124L, 12L, 500, 2, 3003L);

        cart.addItem(defaultItem1);
        cart.addItem(defaultItem2);

        double expectedTotalAmountAfterDiscount = 9000;

        assertEquals(expectedTotalAmountAfterDiscount, cart.calculateTotalAmountAfterDiscount());
    }


    @Test
    void shoud_Apply_SameCategoryPromotion(){
        /*
           Same category -> %5 ,   Toplam tutar: 55000 , İndirim: 2750
        * */
        DefaultItem defaultItem1 = new DefaultItem(123L, 11L, 50000, 1, 3003L);
        DefaultItem defaultItem2 = new DefaultItem(124L, 12L, 2500, 2, 3003L);

        cart.addItem(defaultItem1);
        cart.addItem(defaultItem2);

        double expectedTotalAmountAfterDiscount = 52250;
        assertEquals(expectedTotalAmountAfterDiscount, cart.calculateTotalAmountAfterDiscount());
    }

    @Test
    void should_Apply_TotalPricePromotion(){
        /*
             5000 - 10000 arası  500,   Toplam: 7000 , İndirim: 500
        * */
        DefaultItem defaultItem1 = new DefaultItem(123L, 11L, 6500, 1, 3001L);
        DefaultItem defaultItem2 = new DefaultItem(124L, 12L, 250, 2, 3003L);

        cart.addItem(defaultItem1);
        cart.addItem(defaultItem2);

        double expectedTotalAmountAfterDiscount = 6500;

        assertEquals(expectedTotalAmountAfterDiscount, cart.calculateTotalAmountAfterDiscount());
    }
}
