package com.example.eRationBackend.dao.admin;

import com.example.eRationBackend.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Long> {
}
