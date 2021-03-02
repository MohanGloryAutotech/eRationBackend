package com.example.eRationBackend.model.product;

import com.example.eRationBackend.model.product.request.AddProductShop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ShopkeeperProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long pid;
    Double price;
    Double qty;
    Long shopId;
    Boolean available;
    Date createdDate;

    public ShopkeeperProduct(AddProductShop addProductShop) {
        this.pid = addProductShop.getPid();
        this.price=addProductShop.getPrice();
        this.qty = addProductShop.getQty();
        this.shopId = addProductShop.getShopId();
        this.available=true;
    }

    public ShopkeeperProduct(ShopkeeperProduct shopkeeperProductExist) {
        this.pid = shopkeeperProductExist.getPid();
        this.price=shopkeeperProductExist.getPrice();
        this.qty = shopkeeperProductExist.getQty();
        this.shopId = shopkeeperProductExist.getShopId();
        this.id = shopkeeperProductExist.getId();
        this.available=true;
    }

    @PrePersist
    public void create()
    {
        this.createdDate=new Date(System.currentTimeMillis());
    }
}
