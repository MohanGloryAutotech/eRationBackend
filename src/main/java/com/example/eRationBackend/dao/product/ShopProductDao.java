package com.example.eRationBackend.dao.product;

import com.example.eRationBackend.model.product.ShopkeeperProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopProductDao extends JpaRepository<ShopkeeperProduct,Long> {

    @Query("select s from ShopkeeperProduct s where s.pid=:pid AND s.shopId=:shopId")
    ShopkeeperProduct getProductByShopAndProdctId(Long pid, Long shopId);

    @Query("select s from ShopkeeperProduct s where s.shopId=:shopId")
    List<ShopkeeperProduct> getAllProductByShopId(Long shopId);
}
