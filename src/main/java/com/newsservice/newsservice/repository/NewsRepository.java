package com.newsservice.newsservice.repository;

import com.newsservice.newsservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n " +
            "LEFT JOIN n.allowedRoles r " +
            "LEFT JOIN r.userList u " +
            "WHERE u.userId = :userId " +
            "AND r.roleId = :roleId " +
            "AND n.readStatus IS NULL")
    List<News> findAllNewsByRoleId(@Param("roleId") Long roleId, @Param("userId") Long userId);
}
