package com.example.eRationBackend.Controller;

import com.example.eRationBackend.ControllerConfig.ControllerConfig;
import com.example.eRationBackend.dao.shopkeeper.CityDao;
import com.example.eRationBackend.dao.shopkeeper.StateDao;
import com.example.eRationBackend.generalResponce.GeneralResponse;
import com.example.eRationBackend.model.City;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StateCityController extends ControllerConfig {

    @Autowired
    StateDao stateDao;

    @Autowired
    CityDao cityDao;

    @GetMapping("/city/get/getById/{id}")
    public GeneralResponse<City> getCityById(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<City> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            City city = cityDao.getCityByCityId(id);
            if(city!=null)
            {
                result =new GeneralResponse<>(city,"data fetchd successfully",true,System.currentTimeMillis(), HttpStatus.FOUND);
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

    @GetMapping("/city/get/getCityByStateId/{id}")
    public GeneralResponse<List<City>> getCityByStateId(@PathVariable(name = "id")Long id)
    {
        GeneralResponse<List<City>> result;

        try {
            if(id==null)
                throw new Exception("data can't be null");

            List<City> shopkeeper = cityDao.getCityByState(id);
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
    @GetMapping("/state/get/all")
    public GeneralResponse<List<State>> getAllState()
    {
        GeneralResponse<List<State>> result;

        try {

            List<State> shopkeeper = stateDao.getAllState();
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
}
