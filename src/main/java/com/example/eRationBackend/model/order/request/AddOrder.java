package com.example.eRationBackend.model.order.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOrder {
    Long pId;
    Long cId;
    Double qty;
    Date appointmentDateTime;
}
