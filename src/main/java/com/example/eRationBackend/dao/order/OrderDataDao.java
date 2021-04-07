package com.example.eRationBackend.dao.order;

import com.example.eRationBackend.model.order.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataDao extends JpaRepository<OrderData,Long> {
}
