package com.sawi_health.demo.model;

public enum Status {
    REPORTED,        // Citizen submitted complaint
    VERIFIED,        // Department verified complaint
    ASSIGNED,        // Assigned to field staff
    IN_PROGRESS,     // Work started
    ON_HOLD,         // Work paused (material / weather issue)
    RESOLVED,        // Issue fixed
    REJECTED,        // Invalid complaint
    REOPENED,        // Citizen reopened complaint
    CLOSED           // Final closure after verification
}