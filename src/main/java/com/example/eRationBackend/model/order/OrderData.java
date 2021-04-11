package com.example.eRationBackend.model.order;

import com.example.eRationBackend.model.order.request.AddOrder;
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
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long controlId;
    Long pId;
    Double qty;


    public OrderData(AddOrder addOrder) {
        this.pId=addOrder.getPId();
        this.qty=addOrder.getQty();
    }
}
