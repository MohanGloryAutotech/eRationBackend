package com.example.eRationBackend.dao.cart;

import com.example.eRationBackend.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDao extends JpaRepository<Cart,Long> {

    @Query("select x from Cart x where x.controlId=:id ")
    List<Cart> getAllCartByCustomerId(Long id);
}
