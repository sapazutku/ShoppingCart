package com.trendyol.bootcamp.shoppingcart.domain.model.Promotion;

import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;

import java.util.Set;
import java.util.stream.Collectors;

public class SameSellerPromotion extends Promotion{

    public SameSellerPromotion() {
        this.id =  9909L;
        this.discountRatio = 0.1;
        this.discountAmount = 0;
    }

    public double calculateDiscount(Cart cart){
        return cart.calculateTotalAmountWithoutDiscount() * discountRatio - discountAmount;
    }

    @Override
    public boolean isApplicable(Cart cart){
        Set<Long> sellerIds = cart.getItems().stream().map(item -> item.getSellerId()).collect(Collectors.toSet());
        return sellerIds.size() == 1;
    }


}
