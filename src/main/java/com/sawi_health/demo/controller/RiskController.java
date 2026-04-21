package com.sawi_health.demo.controller;


import com.sawi_health.demo.dto.RiskFormRequest;
import com.sawi_health.demo.dto.RiskResultRequest;
import com.sawi_health.demo.model.RiskResult;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.service.RiskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk")
@CrossOrigin("*")
public class RiskController {

    private final RiskService service;

    public RiskController(RiskService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public RiskResult save(@RequestBody RiskResultRequest request) {
        return service.save(request);
    }

    @GetMapping("/reports")
    public List<RiskResult> getAllReports() {
        return service.getAllReports();
    }
    @PostMapping("form/save-type")
    public ResponseEntity<?> saveRisk(@RequestBody RiskFormRequest riskFormRequest) {
        service.Riskform(riskFormRequest);
        return ResponseEntity.ok("Saved");
    }
}