package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.customer.request.AddRationCard;
import com.example.eRationBackend.serviceImpl.ShopkeepeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopkeeperController extends ControllerConfig {

    @Autowired
    ShopkeepeServiceImpl shopkeepeService;

    @PostMapping("/shopkeeper/add/")
    public GeneralResponse<Boolean> addShopkeeperRecord(@RequestBody Shopkeeper shopkeeper)
    {
        GeneralResponse<Boolean> result;

        try {
            if(shopkeeper==null)
                throw new Exception("data can't be null");

            shopkeepeService.saveShopkeeperDetail(shopkeeper);
            result =new GeneralResponse<Boolean>(true,"data added successfully",true,System.currentTimeMillis(), HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<Boolean>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @GetMapping("/shopkeeper/get/getById/{id}")
    public GeneralResponse<Shopkeeper> getShopkeeperById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<Shopkeeper> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            Shopkeeper shopkeeper = shopkeepeService.getShopkeeperById(id);
            if(shopkeeper!=null)
            {
                result =new GeneralResponse<>(shopkeeper,"data fetchd successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
            }else {
                result =new GeneralResponse<>(null,"data not found",false,System.currentTimeMillis(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @GetMapping("/shopkeeper/get/getByCityId/{id}")
    public GeneralResponse<List<Shopkeeper>> getShopkeeperByCityId(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<List<Shopkeeper>> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            List<Shopkeeper> shopkeeper = shopkeepeService.getShopkeeperByCityId(id);
            if(!shopkeeper.isEmpty())
            {
                result =new GeneralResponse<>(shopkeeper,"data fetchd successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
            }else {
                result =new GeneralResponse<>(null,"data not found",false,System.currentTimeMillis(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }
    @GetMapping("/shopkeeper/get/getByStateId/{id}")
    public GeneralResponse<List<Shopkeeper>> getShopkeeperByStateId(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<List<Shopkeeper>> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            List<Shopkeeper> shopkeeper = shopkeepeService.getShopkeeperByStateId(id);
            if(!shopkeeper.isEmpty())
            {
                result =new GeneralResponse<>(shopkeeper,"data fetched successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
            }else {
                result =new GeneralResponse<>(null,"data not found",false,System.currentTimeMillis(), HttpStatus.NOT_FOUND);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(null,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

    @PutMapping("/shopkeeper/update/updateShopStatus/{id}/{status}")
    public GeneralResponse<Boolean> updateShopStatusById(@PathVariable(name = "id")Long id,@PathVariable(name = "status")Boolean status)
    {
        GeneralResponse<Boolean> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            shopkeepeService.updateShopStatus(id,status);
            result =new GeneralResponse<>(true,"data updated successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);

        }catch (Exception e)
        {
            e.printStackTrace();
            result =new GeneralResponse<>(false,e.getMessage(),false,System.currentTimeMillis(), HttpStatus.BAD_REQUEST);
        }

        return result;

    }

}
