package com.sawi_health.demo.dto;

import lombok.Data;

@Data
public class InvestmentResponseDto {

    private Long id;
    private String name;
    private String riskType;
    private String username;

    private double expectedReturn;
    private double minInvestment;

    private String goal;
    private int horizonYears;

    private double equityPercent;
    private double debtPercent;
    private double goldPercent;

    private String strategy;
    private String status;
}