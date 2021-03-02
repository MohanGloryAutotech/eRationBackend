package com.example.eRationBackend.model.product.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddProductShop {

    Long pid;
    Double qty;
    Double price;
    Long shopId;
}
