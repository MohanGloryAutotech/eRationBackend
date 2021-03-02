package com.example.eRationBackend.dao.product;

import com.example.eRationBackend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Long> {


    @Query("Select p from Product p where LOWER(p.productName)=LOWER(:productName)")
    Product getProductByName(String productName);

    @Query("select p from Product p where p.id=:id")
    Product getProductById(Long id);

    @Query("select s from Product s")
    List<Product> getALlProduct();
}
