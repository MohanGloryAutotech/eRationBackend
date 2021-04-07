package com.example.eRationBackend.dao.order;

import com.example.eRationBackend.model.order.OrderMast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderMastDao extends JpaRepository<OrderMast,Long> {
    @Query("select x from OrderMast x where x.cId=:id")
    List<OrderMast> getOrderByCid(Long id);

    @Query("select x from OrderMast x where x.id=:id")
    OrderMast getOrderById(Long id);
}
