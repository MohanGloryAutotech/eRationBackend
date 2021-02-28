package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.customer.request.AddRationCard;
import com.example.eRationBackend.model.otpVerification.OtpVerification;
import com.example.eRationBackend.serviceImpl.RationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RationController extends ControllerConfig {


    @Autowired
    RationServiceImpl rationService;

    @PostMapping("/ration/add/")
    public GeneralResponse<Boolean> addRationCard(@RequestBody AddRationCard addRationCard)
    {
        GeneralResponse<Boolean> result;

        try {
            if(addRationCard==null)
                throw new Exception("data can't be null");

            rationService.saveRationDetail(addRationCard);
            result =new GeneralResponse<Boolean>(true,"data added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<Boolean>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @GetMapping("/ration/get/getByRation/{id}")
    public GeneralResponse<AddRationCard> getRationCardByRationNumber(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<AddRationCard> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            AddRationCard rationCard = rationService.getRationDetailByRationId(id);
            result =new GeneralResponse<>(rationCard,"data fetched successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }


    @GetMapping("/ration/get/getById/{id}")
    public GeneralResponse<AddRationCard> getRationCardById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<AddRationCard> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            AddRationCard rationCard = rationService.getRationDetailById(id);
            result =new GeneralResponse<>(rationCard,"data fetched successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @GetMapping("/ration/get/getAll")
    public GeneralResponse<List<AddRationCard>> getAllRationCard()
    {
        GeneralResponse<List<AddRationCard>> result;

        try {

            List<AddRationCard> rationCard = rationService.getAlRationCard();
            result =new GeneralResponse<>(rationCard,"data fetched successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @GetMapping("/ration/get/getOtp/{ratioNo}")
    public GeneralResponse<OtpVerification> getOtpForRationCard(@PathVariable(name="ratioNo") String ratioNo)
    {
        GeneralResponse<OtpVerification> result;

        try {

            OtpVerification rationCard = rationService.getOtpForRationCardNo(ratioNo);
            result =new GeneralResponse<>(rationCard,"OTP send successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }



}
