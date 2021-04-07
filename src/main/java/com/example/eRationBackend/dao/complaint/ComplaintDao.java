package com.example.eRationBackend.dao.complaint;

import com.example.eRationBackend.model.complaint.ComplainResponse;
import com.example.eRationBackend.model.complaint.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComplaintDao extends JpaRepository<Complaint,Long> {
    @Query("select new com.example.eRationBackend.model.complaint.ComplainResponse(c,(select e.name from Employee e where e.id=c.empId)) from Complaint c where c.cId=:cid")
    List<ComplainResponse> getComplainResponseByCid(Long cid);

    @Query("select new com.example.eRationBackend.model.complaint.ComplainResponse(c,(select e.name from Employee e where e.id=c.empId)) from Complaint c where c.id=:id")
    ComplainResponse getComplainResponseById(Long id);

    @Query("select x from Complaint x where x.id=:id")
    Complaint getComplainById(Long id);

    @Modifying
    @Transactional
    @Query("update Complaint x set x.status=:status where x.id=:id")
    void updateStatusById(Long id, Boolean status);
}
