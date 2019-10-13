package com.newsservice.newsservice.repository;

import com.newsservice.newsservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
