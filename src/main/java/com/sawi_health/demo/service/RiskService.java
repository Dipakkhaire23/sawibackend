package com.sawi_health.demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sawi_health.demo.config.AuthUtil;
import com.sawi_health.demo.dto.RiskFormRequest;
import com.sawi_health.demo.dto.RiskResultRequest;
import com.sawi_health.demo.model.RiskResult;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.RiskResultRepository;
import com.sawi_health.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {

    private final RiskResultRepository repository;
    private final ObjectMapper objectMapper;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    public UserRepository userRepository;

    public RiskService(RiskResultRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }
    public List<RiskResult> getAllReports() {
        return repository.findAll();
    }

    public RiskResult save(RiskResultRequest request) {
        try {
            RiskResult entity = RiskResult.builder()
                    .totalScore(request.getTotalScore())
                    .category(request.getCategory())
                    .persona(request.getPersona())
                    .schemes(request.getSchemes())
                    .analysis(request.getAnalysis())

                    // 🔥 Convert object → JSON string
                    .chartData(objectMapper.writeValueAsString(request.getChartData()))
                    .radarData(objectMapper.writeValueAsString(request.getRadarData()))
                    .areaData(objectMapper.writeValueAsString(request.getAreaData()))
                    .allocationData(objectMapper.writeValueAsString(request.getAllocationData()))
                    .comparisonData(objectMapper.writeValueAsString(request.getComparisonData()))
                    .horizonData(objectMapper.writeValueAsString(request.getHorizonData()))
                    .recommendations(objectMapper.writeValueAsString(request.getRecommendations()))
                    .build();

            return repository.save(entity);

        } catch (Exception e) {
            throw new RuntimeException("Error saving risk result", e);
        }
    }

    public void Riskform(RiskFormRequest riskFormRequest) {
        User user = authUtil.getLoggedInUser();
        user.setRiskType(riskFormRequest.getRiskType());

        user.setRiskScore(riskFormRequest.getScore());
        userRepository.save(user);

    }
}