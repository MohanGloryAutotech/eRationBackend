package com.example.eRationBackend.model.cart;

import com.example.eRationBackend.model.cart.request.ProductList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long controlId;
    Long pId;
    Double qty;

    public CartItem(ProductList productList) {
        this.pId=productList.getPId();
        this.qty = productList.getQty();
    }
}
