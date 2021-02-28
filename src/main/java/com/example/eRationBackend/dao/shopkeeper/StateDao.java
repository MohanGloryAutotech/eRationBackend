package com.example.eRationBackend.dao.shopkeeper;

import com.example.eRationBackend.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface StateDao extends JpaRepository<State,Long> {

    @Query("select s from State s where s.stateId=:stateId")
    State getStateById(Long stateId);

    @Query("select s from State s ")
    List<State> getAllState();
}
