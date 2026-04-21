package com.sawi_health.demo.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalScore;
    private String category;
    private String persona;
    private String schemes;

    @Column(length = 3000)
    private String analysis;

    // 🔥 Store complex JSON as String
    @Lob
    private String chartData;

    @Lob
    private String radarData;

    @Lob
    private String areaData;

    @Lob
    private String allocationData;

    @Lob
    private String comparisonData;

    @Lob
    private String horizonData;

    @Lob
    private String recommendations;
}