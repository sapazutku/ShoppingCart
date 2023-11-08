package com.trendyol.bootcamp.shoppingcart.domain.model.Promotion;

import com.trendyol.bootcamp.shoppingcart.domain.model.Cart;
import org.springframework.context.annotation.Lazy;

public class TotalPricePromotion extends Promotion{

    public TotalPricePromotion(){
        this.id = 1232L;
        this.discountRatio = 0;
    }

    public double calculateDiscount(Cart cart){
        if (cart.calculateTotalAmountWithoutDiscount() < 5000) this.discountAmount =  - 250;
        else if (cart.calculateTotalAmountWithoutDiscount() < 10000) this.discountAmount = - 500;
        else if (cart.calculateTotalAmountWithoutDiscount() < 50000) this.discountAmount = - 1000;
        else this.discountAmount = - 2000;
        return discountAmount;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return true;
    }

}
