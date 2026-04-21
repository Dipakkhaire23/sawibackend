package com.sawi_health.demo.dto;


import lombok.Data;

@Data
public class RiskResultRequest {

    private int totalScore;
    private String category;
    private String persona;
    private String schemes;
    private String analysis;

    private Object chartData;
    private Object radarData;
    private Object areaData;
    private Object allocationData;
    private Object comparisonData;
    private Object horizonData;
    private Object recommendations;
}