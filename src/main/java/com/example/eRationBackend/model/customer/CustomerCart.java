package com.example.eRationBackend.model.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CustomerCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long cid;
    Date createdDate;
    Boolean status;
    Boolean type;


}
