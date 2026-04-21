package com.sawi_health.demo.dto;



import com.sawi_health.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;

    private Long id;
    private  String name;
    private Role role;




}
