package com.example.eRationBackend.dao.employee;

import com.example.eRationBackend.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee,Long> {
    @Query("select x from Employee x")
    List<Employee> getAllEmployee();

    @Query("select x from Employee x where x.id=:id")
    Employee getEmployeeById(Long id);

    @Query(value = "select * from employee as x where x.city_id=:cityId ORDER BY x.count ASC LIMIT 1 ",nativeQuery = true)
    Employee getEmployeeByCityId(@Param("cityId") Long cityId);
}
