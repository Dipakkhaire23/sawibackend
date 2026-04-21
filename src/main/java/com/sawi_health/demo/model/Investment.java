package com.sawi_health.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String riskType;

    private double expectedReturn;
    private double minInvestment;

    // 🔥 NEW FIELDS
    private String goal; // WEALTH / RETIREMENT / SHORT_TERM
    private int horizonYears;

    private double equityPercent;
    private double debtPercent;
    private double goldPercent;

    private String strategy; // text explanation

    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}