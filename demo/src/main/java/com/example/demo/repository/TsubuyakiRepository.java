package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.TsubuyakiEntity;

public interface TsubuyakiRepository extends JpaRepository<TsubuyakiEntity,Long> {
    
    @Query("SELECT t FROM TsubuyakiEntity t WHERE t.comment LIKE %:keyword%")
    List<TsubuyakiEntity> searchTsubuyaki(@Param("keyword") String keyword);
}
