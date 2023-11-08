package com.trendyol.bootcamp.shoppingcart.domain.model;

import com.trendyol.bootcamp.shoppingcart.application.exception.ItemQuantityException;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DefaultItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.DigitalItem;
import com.trendyol.bootcamp.shoppingcart.domain.model.Item.Item;
import com.trendyol.bootcamp.shoppingcart.domain.model.Promotion.CategoryPromotion;
import com.trendyol.bootcamp.shoppingcart.domain.model.Promotion.Promotion;
import com.trendyol.bootcamp.shoppingcart.domain.model.Promotion.SameSellerPromotion;
import com.trendyol.bootcamp.shoppingcart.domain.model.Promotion.TotalPricePromotion;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Cart  {

    private final int MAX_NUMBER_OF_ITEMS = 30;
    private List<Item> items = new ArrayList<>(MAX_NUMBER_OF_ITEMS);
    private double totalAmountAfterDiscount;
    private Promotion selectedPromotion;

    private final List<Promotion> promotions = new ArrayList<>();

    {
        promotions.add(new CategoryPromotion());
        promotions.add(new SameSellerPromotion());
        promotions.add(new TotalPricePromotion());
    }

    public Cart() {
    }

    public Cart( List<Item> items) {
        this.items = items;
    }

    public double calculateTotalAmountWithoutDiscount(){
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum()
                +  items.stream().filter(item -> item instanceof DefaultItem).mapToDouble(item -> ((DefaultItem) item).calculateVasItemsPrice()).sum();
    }

    public double calculateTotalAmountAfterDiscount(){
        applyBestPromotion();
        return totalAmountAfterDiscount;
    }

    public List<Promotion> findApplicablePromotions(){
        return promotions.stream().filter(promotion -> promotion.isApplicable(this)).collect(Collectors.toList());
    }

    public double calculateVasItemsPrice(DefaultItem item){
        return item.calculateVasItemsPrice();
    }

    public Promotion findBestPromotion(){
        List<Promotion> applicablePromotions = findApplicablePromotions();
        Optional<Promotion> bestPromotion = applicablePromotions.stream().max(Comparator.comparingDouble(promotion ->
                promotion.calculateDiscount(this)));
        return bestPromotion.orElse(null);
    }

    public void applyBestPromotion(){
        Promotion bestPromotion = findBestPromotion();
        double newTotalAmount = calculateTotalAmountWithoutDiscount() - bestPromotion.calculateDiscount(this);
        if (newTotalAmount > 500000) {
            throw new RuntimeException("Total amount after discount can't exceed 500,000.");
        }
        if (newTotalAmount < 0){
            newTotalAmount = 0;
        }
        this.totalAmountAfterDiscount = newTotalAmount;
        this.selectedPromotion = bestPromotion;
    }

    public void addItem(Item item) {
        // if item digital, cart should not contain default item
        if (item instanceof DigitalItem && containsOnlyDefaultItems()) {
            throw new RuntimeException("Cart should not contain default item");
        }
        // if item default, cart should not contain digital item
        if (item instanceof DefaultItem && containsOnlyDigitalItems()) {
            throw new RuntimeException("Cart should not contain digital item");
        }
        // if item id contains before in the cart, increase quantity
        if (findById(item.getId()) != null) {
            increaseQuantityOfItem(item);
        }
        else {
            controlQuantity(item);
            items.add(item);
        }
    }

    public void increaseQuantityOfItem(Item item){
        if (findById(item.getId()).getQuantity() + item.getQuantity() > item.getMAX_QUANTITY_IN_CART()) {
            throw new ItemQuantityException("Quantity can't exceed " + item.getMAX_QUANTITY_IN_CART());
        }
        findById(item.getId()).setQuantity(findById(item.getId()).getQuantity() + item.getQuantity());
    }

    public void controlQuantity(Item item) {
        if (item.getQuantity() > item.getMAX_QUANTITY_IN_CART()) {
            throw new ItemQuantityException("Quantity can't exceed " + item.getMAX_QUANTITY_IN_CART());
        }
    }

    public boolean containsOnlyDigitalItems() {
        return !this.getItems().isEmpty() &&
                this.getItems().stream().allMatch(item -> item instanceof DigitalItem);
    }

    public boolean containsOnlyDefaultItems() {
        return !this.getItems().isEmpty() &&
                this.getItems().stream().allMatch(item -> item instanceof DefaultItem);
    }


    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item findById(Long id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    public DefaultItem findDefaultItemById(Long id) {
        return items.stream().filter(item -> item.getId().equals(id) && item instanceof DefaultItem).map(item -> (DefaultItem) item).findFirst().orElse(null);
    }

    public void resetCart() {
        items.clear();
    }

    // GETTER and SETTER

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Promotion getSelectedPromotion() {
        return selectedPromotion;
    }

    public void setSelectedPromotion(Promotion selectedPromotion) {
        this.selectedPromotion = selectedPromotion;
    }

}
