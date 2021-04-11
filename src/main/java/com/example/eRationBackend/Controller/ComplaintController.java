package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.complaint.ComplainResponse;
import com.example.eRationBackend.model.complaint.Complaint;
import com.example.eRationBackend.model.employee.Employee;
import com.example.eRationBackend.serviceImpl.ComplainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComplaintController extends ControllerConfig {

    @Autowired
    ComplainServiceImpl complainService;


    @PostMapping("/complain/add")
    public GeneralResponse<Boolean> createComplain(@RequestBody Complaint record)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(record==null)
                throw new Exception("info can't be null");

            complainService.addComplain(record);
            response=new GeneralResponse<>(true,"Complain added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            response=new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/complain/getComplain/{cid}")
    public GeneralResponse<List<ComplainResponse>> getComplainByCid(@PathVariable(name = "cid") Long cid)
    {
        GeneralResponse<List<ComplainResponse>> response=null;
        try {

            if(cid==null)
                throw new Exception("info can't be null");

            List<ComplainResponse> complaintList =  complainService.getComplainResponseByCid(cid);
            if(!complaintList.isEmpty())
            response=new GeneralResponse<>(complaintList,"Complain fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            else
            response=new GeneralResponse<>(complaintList,"data not found",false,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/complain/getComplain/all")
    public GeneralResponse<List<ComplainResponse>> getAllComplain()
    {
        GeneralResponse<List<ComplainResponse>> response=null;
        try {



            List<ComplainResponse> complaintList =  complainService.getAllComplaint();
            if(!complaintList.isEmpty())
                response=new GeneralResponse<>(complaintList,"Complain fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            else
                response=new GeneralResponse<>(complaintList,"data not found",false,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
    @GetMapping("/complain/getComplainBy/{id}")
    public GeneralResponse<ComplainResponse> getComplainById(@PathVariable(name = "id") Long id)
    {
        GeneralResponse<ComplainResponse> response=null;
        try {

            if(id==null)
                throw new Exception("info can't be null");

            ComplainResponse complaintList =  complainService.getComplainResponseById(id);
            if(complaintList!=null)
                response=new GeneralResponse<>(complaintList,"Complain fetched successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
            else
                response=new GeneralResponse<>(complaintList,"data not found",false,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/complain/updateStatus/{status}/{id}")
    public GeneralResponse<Boolean> updateComplainResponse(@PathVariable(name = "id") Long id,@PathVariable(name = "status") Boolean status)
    {
        GeneralResponse<Boolean> response=null;
        try {

            if(id==null || status==null)
                throw new Exception("info can't be null");

            complainService.updateComplainStatusById(id,status);
                response=new GeneralResponse<>(true,"Complain updated successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);

        }catch (Exception e)
        {
            e.printStackTrace();
            response=new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
