package com.sawi_health.demo.controller;

import com.sawi_health.demo.config.AuthUtil;
import com.sawi_health.demo.dto.InvestmentRequestDto;
import com.sawi_health.demo.dto.InvestmentResponseDto;
import com.sawi_health.demo.model.Investment;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.UserRepository;
import com.sawi_health.demo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtil authUtil;



    @PostMapping("/admin/investments")
    public ResponseEntity<InvestmentResponseDto> create(@RequestBody InvestmentRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
    @GetMapping("/admin/GetAll")
    public ResponseEntity<List<InvestmentResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

//    // 🔥 USER GET PLANS BASED ON RISK
@GetMapping("/user/plans")
public ResponseEntity<List<InvestmentResponseDto>> getUserPlans() {

    User user = authUtil.getLoggedInUser();

    List<InvestmentResponseDto> plans = service.getPlansByUser(user);

    return ResponseEntity.ok(plans);
}

    // 🔥 SAVE USER RISK TYPE
    @PostMapping("/risk/save-type")
    public ResponseEntity<?> saveRisk(@RequestBody Map<String, String> req) {
        User user = authUtil.getLoggedInUser();

        user.setRiskType(req.get("riskType"));
        userRepository.save(user);

        return ResponseEntity.ok("Saved");
    }
}
