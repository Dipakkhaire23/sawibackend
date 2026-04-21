package com.sawi_health.demo.repository;


import com.sawi_health.demo.model.RiskResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskResultRepository extends JpaRepository<RiskResult, Long> {
}