package com.example.eRationBackend.model;

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
public class Shopkeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Boolean active;//license status
    Boolean open;//open store or not
    String address;
    String area;
    String subArea;
    Long cityId;
    Long stateId;
    String licenceNumber;
    String username;
    String password;
    String contact;
    Date createdDate;

    @PrePersist
    public void create()
    {
        this.createdDate=new Date(System.currentTimeMillis());
    }


}
