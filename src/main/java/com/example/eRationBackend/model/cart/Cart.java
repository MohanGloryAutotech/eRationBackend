package com.example.eRationBackend.model.cart;

import com.example.eRationBackend.model.cart.request.AddCart;
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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long controlId;
    String type=CartType.shop.toString();
    Date createdDate;
    Boolean status=false;

    public Cart(AddCart record) {
        this.controlId=record.getCId();
    }


    @PrePersist
    public void onCreate()
    {
        this.createdDate = new Date(System.currentTimeMillis());
    }


}
