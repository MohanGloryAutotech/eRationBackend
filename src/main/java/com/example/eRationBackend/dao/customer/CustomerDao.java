package com.example.eRationBackend.dao.customer;

import com.example.eRationBackend.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c where c.rationNumber=:rationNumber")
    Customer getRationByNumber(Long rationNumber);

    @Query("select c from Customer c where c.id=:id")
    Customer getRationCardDetailById(Long id);

    @Query("select c from Customer c")
    List<Customer> getAllCustomer();
}
