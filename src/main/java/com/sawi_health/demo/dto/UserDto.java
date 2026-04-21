package com.sawi_health.demo.dto;




import com.sawi_health.demo.model.AuthProvider;
import com.sawi_health.demo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;
    private AuthProvider provider;
    private String riskType; // AGGRESSIVE / MODERATE / CONSERVATIVE



    private String imagetype;
    private  String imagename;
    private byte[] imagedata;





}
