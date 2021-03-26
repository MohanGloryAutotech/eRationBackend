package com.example.eRationBackend.dao.cart;

import com.example.eRationBackend.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItem,Long> {

    @Query("select x from CartItem x where x.controlId=:id")
    List<CartItem> findByControlId(Long id);
}
