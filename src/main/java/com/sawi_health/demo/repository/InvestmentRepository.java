package com.sawi_health.demo.repository;

import com.sawi_health.demo.model.Investment;
import com.sawi_health.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findByRiskType(String riskType);
    List<Investment> findByUser(User user);
    @Modifying
    @Transactional
    @Query("DELETE FROM Investment i WHERE i.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}