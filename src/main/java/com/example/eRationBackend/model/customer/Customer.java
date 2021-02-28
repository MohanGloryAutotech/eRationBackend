package com.example.eRationBackend.model.customer;

import lombok.*;

import javax.persistence.*;
import javax.sound.midi.SysexMessage;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String subArea;
    String area;
    String address;
    Long rationNumber;
    Long cityId;
    Long categoryId;
    Long stateId;
    Long shopId;
    String email;
    String contact;
    Date createdDate;

    @PrePersist
    public void create()
    {
        this.createdDate=new Date(System.currentTimeMillis());
    }


    public Customer(Customer customer) {
        this.id = customer.id;
        this.name=customer.name;
        this.subArea=customer.subArea;
        this.area= customer.getArea();
        this.rationNumber=customer.getRationNumber();
        this.cityId=customer.cityId;
        this.categoryId=customer.categoryId;
        this.stateId=customer.stateId;
        this.shopId= customer.getShopId();
        this.email=customer.getEmail();
        this.contact=customer.getContact();
    }
}
