package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.employee.EmployeeDao;
import com.example.eRationBackend.dao.shopkeeper.CityDao;
import com.example.eRationBackend.model.City;
import com.example.eRationBackend.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    CityDao cityDao;

    public void addEmployee(Employee emp) throws Exception {
        //check the city is exist with state or not

        City cityExist = cityDao.getCityWithStateId(emp.getCityId(), emp.getStateId());
        if(cityExist==null)
            throw new Exception("please enter right city");

        employeeDao.save(emp);
    }

    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }
}
