package com.example.eRationBackend.dao.customer;

import com.example.eRationBackend.model.customer.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItems,Long> {
}
