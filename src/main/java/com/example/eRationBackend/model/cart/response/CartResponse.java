package com.example.eRationBackend.model.cart.response;


import com.example.eRationBackend.model.cart.Cart;
import com.example.eRationBackend.model.cart.CartItem;

import java.util.List;

public class CartResponse {
    Cart cart;
    List<CartItem> cartItemList;

    public CartResponse(Cart cart, List<CartItem> cartItemList) {
        this.cart=cart;
        this.cartItemList=cartItemList;
    }
}
