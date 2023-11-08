package com.trendyol.bootcamp.shoppingcart.application.exception;

public class ItemQuantityException extends RuntimeException{
    public ItemQuantityException(String message) {
        super(message);
    }
}
