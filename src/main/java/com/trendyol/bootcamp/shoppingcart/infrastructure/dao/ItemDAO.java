package com.trendyol.bootcamp.shoppingcart.infrastructure.dao;

import com.trendyol.bootcamp.shoppingcart.infrastructure.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemDAO extends JpaRepository<ItemEntity, Long> {

}
