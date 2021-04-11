package com.example.eRationBackend.model.order.request;

import com.example.eRationBackend.model.order.OrderData;
import com.example.eRationBackend.model.order.OrderMast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderMastResponse {
    Long id;
    Double amt;
    Long cId;
    Date createdDate;
    Boolean isAppointment;
    Date appointmentDate;
    Boolean status;//false not done
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "controlId", referencedColumnName = "id")
    List<OrderDataResponse> orderDataList;

    public OrderMastResponse(OrderMast orderById) {
        this.id=orderById.getId();
        this.amt=orderById.getAmt();
        this.cId=orderById.getCId();
        this.createdDate=orderById.getCreatedDate();
        this.isAppointment=orderById.getIsAppointment();
        this.appointmentDate=orderById.getAppointmentDate();
        this.status = orderById.getStatus();//false not done
    }
}
