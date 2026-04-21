package com.sawi_health.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeetingDto {

    private Long id;
    private String topic;
    private String description;
    private LocalDateTime meetingTime;
    private String meetLink;
}