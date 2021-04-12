package com.example.eRationBackend.model.product;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productName;
    byte[] img;

}
