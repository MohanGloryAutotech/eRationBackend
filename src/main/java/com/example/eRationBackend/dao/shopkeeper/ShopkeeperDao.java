package com.example.eRationBackend.dao.shopkeeper;


import com.example.eRationBackend.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopkeeperDao extends JpaRepository<Shopkeeper,Long> {


    @Query("select s from Shopkeeper s where s.username=:username")
    Shopkeeper getShopkeeperUserName(String username);

    @Query("select s from Shopkeeper s where s.id=:id")
    Shopkeeper getShopkeeperById(Long id);

    @Query("select s from Shopkeeper s where s.cityId=:id")
    List<Shopkeeper> getShopkeeperByCityId(Long id);

    @Query("select s from Shopkeeper s where s.stateId=:id")
    List<Shopkeeper> getShopkeeperByStateId(Long id);

    @Modifying
    @Transactional
    @Query("update Shopkeeper s set s.open=:status where s.id=:id")
    void updateShopStatusById(Long id, Boolean status);

    @Query("select s from Shopkeeper s ")
    List<Shopkeeper> getAllShopkeeper();
}
