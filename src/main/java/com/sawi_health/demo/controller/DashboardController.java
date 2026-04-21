package com.sawi_health.demo.controller;

import com.sawi_health.demo.dto.DashboardStatsDto;
import com.sawi_health.demo.model.Consultation;
import com.sawi_health.demo.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService service;

    // 🔥 STATS API
    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDto> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    // 🔥 RECENT CONSULTATIONS
    @GetMapping("/recent-consultations")
    public ResponseEntity<List<Consultation>> getRecent() {
        return ResponseEntity.ok(service.getRecentConsultations());
    }
}