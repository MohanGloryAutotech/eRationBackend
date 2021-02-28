package com.example.eRationBackend.dao.shopkeeper;

import com.example.eRationBackend.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopkeeperDao extends JpaRepository<Shopkeeper,Long> {
}
