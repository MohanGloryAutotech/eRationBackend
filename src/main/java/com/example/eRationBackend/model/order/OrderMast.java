package com.example.eRationBackend.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderMast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Double amt;
    Long cId;
    Date createdDate;
    Boolean isAppointment;
    Date appointmentDate;
    Boolean status;//false not done
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlId", referencedColumnName = "id")
    List<OrderData> orderDataList;
    @PrePersist
    public void create()
    {
        this.createdDate=new Date(System.currentTimeMillis());
    }

}
