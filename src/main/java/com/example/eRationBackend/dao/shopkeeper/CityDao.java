package com.example.eRationBackend.dao.shopkeeper;

import com.example.eRationBackend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface CityDao extends JpaRepository<City,Long> {
    @Query("select c from City c where c.cityId=:cityId AND c.stateId=:stateId")
    City getCityWithStateId(Long cityId, Long stateId);

    @Query("select c from City c where c.stateId=:id")
    List<City> getCityByState(Long id);

    @Query("select c from City c where c.cityId=:id")
    City getCityByCityId(Long id);
}
