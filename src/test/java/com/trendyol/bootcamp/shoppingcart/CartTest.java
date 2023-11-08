package com.trendyol.bootcamp.shoppingcart;

import com.trendyol.bootcamp.shoppingcart.application.exception.ItemQuantityException;
import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CartTest {

	private Cart cart;
	@BeforeEach
	void setUp() {
		cart = new Cart();
	}

	@Test
	void should_Have_Maximum_10_Same_Items() {

		DefaultItem defaultItem1 = new DefaultItem(123L, 12L, 3, 10, 3003L);
		DefaultItem defaultItem2 = new DefaultItem(123L, 12L, 3, 1, 3003L);

		cart.addItem(defaultItem1);

		assertThrows(ItemQuantityException.class, () -> cart.addItem(defaultItem2));
		assertEquals(10, cart.getItems().stream().filter(item -> item.getId().equals(defaultItem1.getId()))
				.map(Item::getQuantity).findFirst().get());
	}

	@Test
	void should_Have_Maximum_30_Items() {

		DefaultItem defaultItem1 = new DefaultItem(123L, 12L, 3, 10, 3003L);
		DefaultItem defaultItem2 = new DefaultItem(124L, 12L, 3, 10, 3003L);
		DefaultItem defaultItem3 = new DefaultItem(125L, 12L, 3, 10, 3003L);
		DefaultItem defaultItem4 = new DefaultItem(124L, 12L, 3, 1, 3003L);

		cart.addItem(defaultItem1);
		cart.addItem(defaultItem2);
		cart.addItem(defaultItem3);

		assertThrows(ItemQuantityException.class, () -> cart.addItem(defaultItem4));
		assertEquals(30, cart.getItems().stream().mapToDouble(item -> item.getQuantity()).sum());
	}

	@Test
	void should_Not_Exceed_TotalAmount_500 (){
		DefaultItem defaultItem1 = new DefaultItem(123L, 12L, 100000, 6, 3003L);

		cart.addItem(defaultItem1);

		assertThrows(RuntimeException.class, () -> cart.calculateTotalAmountAfterDiscount());

	}

}
