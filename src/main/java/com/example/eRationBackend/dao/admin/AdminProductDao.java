package com.example.eRationBackend.dao.admin;

import com.example.eRationBackend.model.admin.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductDao extends JpaRepository<AdminProduct,Long> {
}
