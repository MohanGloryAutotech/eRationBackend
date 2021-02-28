package com.example.eRationBackend.serviceImpl;

import com.example.eRationBackend.dao.shopkeeper.CityDao;
import com.example.eRationBackend.dao.shopkeeper.ShopkeeperDao;
import com.example.eRationBackend.dao.shopkeeper.StateDao;
import com.example.eRationBackend.model.City;
import com.example.eRationBackend.model.Shopkeeper;
import com.example.eRationBackend.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopkeeperServiceImpl")
public class ShopkeepeServiceImpl {

    @Autowired
    ShopkeeperDao shopkeeperDao;

    @Autowired
    StateDao stateDao;

    @Autowired
    CityDao cityDao;

    public void saveShopkeeperDetail(Shopkeeper shopkeeper) throws Exception {

        State stateExist = stateDao.getStateById(shopkeeper.getStateId());
        if(stateExist==null)
            throw new Exception("no state found");

        City cityExist = cityDao.getCityWithStateId(shopkeeper.getCityId(),shopkeeper.getStateId());
        if(cityExist==null)
            throw new Exception("no city found");

        Shopkeeper shopkeeperExist = shopkeeperDao.getShopkeeperUserName(shopkeeper.getUsername());

        if(shopkeeperExist!=null)
            throw new Exception("username is already exist");

        shopkeeperDao.save(shopkeeper);

    }

    public Shopkeeper getShopkeeperById(Long id) {
        return shopkeeperDao.getShopkeeperById(id);
    }

    public List<Shopkeeper> getShopkeeperByCityId(Long id) {
        return shopkeeperDao.getShopkeeperByCityId(id);
    }

    public List<Shopkeeper> getShopkeeperByStateId(Long id) {
        return shopkeeperDao.getShopkeeperByStateId(id);
    }

    public void updateShopStatus(Long id, Boolean status) throws Exception {
        Shopkeeper shopkeeperExist = shopkeeperDao.getShopkeeperById(id);
        if(shopkeeperExist==null)
            throw new Exception("no shopkeeper found");

        shopkeeperDao.updateShopStatusById(id,status);

    }
}
