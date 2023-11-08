package com.trendyol.bootcamp.shoppingcart.domain.model.Promotion;

import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryPromotion extends Promotion{
    public CategoryPromotion(){
        this.id = 5676L;
        this.discountRatio = 0.05;
        this.discountAmount = 0;

    }

    public void calculatePromotion(){
        this.id = 5676L;
        this.discountRatio = 0.05;
        this.discountAmount = 0;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        return cart.calculateTotalAmountWithoutDiscount() * discountRatio - discountAmount;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        Set<Long> categoryIds = cart.getItems().stream().map(item -> item.getCategoryId()).collect(Collectors.toSet());
        return categoryIds.size() == 1 && categoryIds.contains(3003L);
    }


}
