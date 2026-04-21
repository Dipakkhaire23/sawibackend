package com.sawi_health.demo.dto;

import lombok.Data;

@Data
public class RiskFormRequest {
    private String riskType;
    private  int score;
}
