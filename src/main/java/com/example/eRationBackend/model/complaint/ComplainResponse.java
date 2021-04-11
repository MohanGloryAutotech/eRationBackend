package com.example.eRationBackend.model.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplainResponse extends Complaint {
    String empName;

    public ComplainResponse(Complaint complaint,String empName) {
        super(complaint);
        this.empName = empName;
    }
}