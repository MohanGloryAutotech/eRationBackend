package com.example.eRationBackend.model.admin;

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
public class AdminProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long qty;
    Double price;
    Long pid;
    Long adminId;
}
