package com.example.eRationBackend.model.complaint;

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
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String description;
    Long shopId;
    Long cityId;
    Long stateId;
    Long cId;
    Date createdDate;
    @Column(columnDefinition = "boolean default false")
    Long status;
    Long empId;

    public Complaint(Complaint complaint) {
        this.id=complaint.getId();
        this.description =complaint.description;
        this.shopId = complaint.getShopId();
        this.cityId = complaint.cityId;
        this.stateId=complaint.getStateId();
        this.cId = complaint.cId;
        this.createdDate = complaint.createdDate;
        this.status=complaint.status;
        this.empId = complaint.empId;
    }

    @PrePersist
    public void onCreate()
    {
        this.createdDate = new Date(System.currentTimeMillis());
    }
}
