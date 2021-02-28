package com.example.eRationBackend.model.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long controlId;
    String name;
    String relation;
    Long age;


    public Member(Member m) {
        this.id=m.getId();
        this.controlId = m.controlId;
        this.name=m.name;
        this.relation=m.relation;
        this.age=m.age;
    }
}
