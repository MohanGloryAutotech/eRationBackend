package com.example.eRationBackend.model.product.request;

import com.example.eRationBackend.model.product.Product;
import com.example.eRationBackend.model.product.ShopkeeperProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllProduct {
    Long pid;
    String pname;
    Double price;
    Double qty;
    byte[] img;
    Long shopId;

    public GetAllProduct(ShopkeeperProduct shopkeeperProduct, Product product) {
        this.pid=product.getId();
        this.pname=product.getProductName();
        this.price=shopkeeperProduct.getPrice();
        this.qty = shopkeeperProduct.getQty();
        this.img=product.getImg();
        this.shopId = shopkeeperProduct.getShopId();
    }
}
