package com.example.eRationBackend.model.cart.request;

import com.example.eRationBackend.model.cart.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCart {


    Long cId;
    List<ProductList> productList;
}
