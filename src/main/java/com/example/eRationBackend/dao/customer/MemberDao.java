package com.example.eRationBackend.dao.customer;

import com.example.eRationBackend.model.customer.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberDao extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.controlId=:id")
    List<Member> getAllMemberByControlId(Long id);
}
