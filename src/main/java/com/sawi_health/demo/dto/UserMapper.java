package com.sawi_health.demo.dto;




import com.sawi_health.demo.model.User;

import java.time.format.DateTimeFormatter;


public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRiskType(user.getRiskType());


        dto.setRole(user.getRole());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        dto.setCreatedate(user.getCreatedAt().format(formatter));

        dto.setImagename(user.getImageName());
        dto.setImagetype(user.getImageType());

//

        dto.setImagedata(user.getImageData());

        return dto;
    }
}

