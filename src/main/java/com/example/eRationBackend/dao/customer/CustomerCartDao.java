package com.example.eRationBackend.dao.customer;

import com.example.eRationBackend.model.customer.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCartDao extends JpaRepository<CustomerCart,Long> {
}
