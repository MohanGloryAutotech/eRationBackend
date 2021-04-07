package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.employee.Employee;
import com.example.eRationBackend.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController extends ControllerConfig {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/employee/add")
    public GeneralResponse<Boolean> createEmployee(@RequestBody Employee emp)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(emp==null)
                throw new Exception("info can't be null");

            employeeService.addEmployee(emp);
            response=new GeneralResponse<>(true,"Emp added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/employee/get/all")
    public GeneralResponse<List<Employee>> getAllEmployee()
    {
        GeneralResponse<List<Employee>> response=null;
        try {


            List<Employee> employeeList = employeeService.getAllEmployee();
            if(!employeeList.isEmpty())
            response=new GeneralResponse<>(employeeList,"Emp fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            else
            response=new GeneralResponse<>(employeeList,"No data found",false,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/employee/get/{id}")
    public GeneralResponse<Employee> getEmployeeById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<Employee> response=null;
        try {


            Employee employeeList = employeeService.getEmployeeById(id);
            if(employeeList!=null)
                response=new GeneralResponse<>(employeeList,"Emp fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            else
                response=new GeneralResponse<>(employeeList,"No data found",false,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
