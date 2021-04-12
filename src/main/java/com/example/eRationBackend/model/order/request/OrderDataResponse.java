package com.example.eRationBackend.model.order.request;

import com.example.eRationBackend.model.order.OrderData;
import com.example.eRationBackend.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDataResponse {
    Long id;
    Long controlId;
    Long pId;
    Double qty;
    String productName;
    byte[] img;


    public OrderDataResponse(OrderData orderData, Product product) {
        this.id=orderData.getId();
        this.controlId=orderData.getControlId();
        this.pId=orderData.getPId();
        this.qty=orderData.getQty();
        this.productName=product.getProductName();
        this.img = product.getImg();
    }
}
