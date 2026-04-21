package com.sawi_health.demo.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;


    private String riskType;

    private String imageType;
    private String imageName;
    private byte[] imageData;
}