package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.complaint.ComplaintDao;
import com.example.eRationBackend.dao.employee.EmployeeDao;
import com.example.eRationBackend.dao.shopkeeper.ShopkeeperDao;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.complaint.ComplainResponse;
import com.example.eRationBackend.model.complaint.Complaint;
import com.example.eRationBackend.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("complainServiceImpl")
public class ComplainServiceImpl {

    @Autowired
    ComplaintDao complaintDao;

    @Autowired
    EmployeeDao employeeDao;


    @Autowired
    ShopkeeperDao shopkeeperDao;

    public void addComplain(Complaint record) throws Exception {
        //check the shop and employee exist with city anf state or not

        Shopkeeper shopkeeperExistWithCity =shopkeeperDao.getShopkeeperByCityIdAndShopId(record.getCityId(),record.getShopId());

        //employee exist for the  city

        Employee employeeExist = employeeDao.getEmployeeByCityId(record.getCityId());
        if(employeeExist==null)
            throw new Exception("no employee exist");

        if(shopkeeperExistWithCity==null)
            throw new Exception("no shopkeeper exist");

        record.setEmpId(employeeExist.getId());
        complaintDao.save(record);

        //update the employee count to +1
        employeeExist.setCount(employeeExist.getCount()+1);
        employeeDao.save(employeeExist);



    }

    public List<ComplainResponse> getComplainResponseByCid(Long cid) {
        return complaintDao.getComplainResponseByCid(cid);
    }

    public ComplainResponse getComplainResponseById(Long id) {
        return complaintDao.getComplainResponseById(id);
    }

    public void updateComplainStatusById(Long id, Boolean status) throws Exception {
        //complain exist or not
        Complaint complaintExist = complaintDao.getComplainById(id);

        if(complaintExist==null)
            throw new Exception("record not found");

        complaintDao.updateStatusById(id,status);

    }
}
