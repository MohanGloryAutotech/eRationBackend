package com.example.eRationBackend.model.cart.response;

import com.example.eRationBackend.model.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCart {
    List<CartResponse> cartResponseList;
}
