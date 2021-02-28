package com.example.eRationBackend.model.customer.request;

import com.example.eRationBackend.model.customer.Customer;
import com.example.eRationBackend.model.customer.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ValueGenerationType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRationCard {
    Customer customer;
    List<Member> memberList;
}
