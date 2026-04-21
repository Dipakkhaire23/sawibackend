package com.sawi_health.demo.dto;

import lombok.Data;

@Data
public class DashboardStatsDto {

    private long totalUsers;
    private long totalInvestments;
    private long totalMeetings;
    private long totalConsultations;
    private long totalReports;
}