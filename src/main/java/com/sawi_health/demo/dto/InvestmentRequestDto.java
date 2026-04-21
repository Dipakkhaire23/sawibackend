package com.sawi_health.demo.dto;

import lombok.Data;

@Data
public class InvestmentRequestDto {

    private String name;
    private Long userId;

    private double expectedReturn;
    private double minInvestment;

    private String goal;
    private int horizonYears;

    private double equityPercent;
    private double debtPercent;
    private double goldPercent;

    private String strategy;
}