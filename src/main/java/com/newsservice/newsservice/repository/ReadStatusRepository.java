package com.newsservice.newsservice.repository;

import com.newsservice.newsservice.model.ReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadStatusRepository extends JpaRepository<ReadStatus, Long> {
}
