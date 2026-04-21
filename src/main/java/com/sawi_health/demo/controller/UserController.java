package com.sawi_health.demo.controller;




import com.sawi_health.demo.config.AuthUtil;
import com.sawi_health.demo.config.JwtUtil;
import com.sawi_health.demo.dto.*;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.UserRepository;
import com.sawi_health.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser() {

        User user = authUtil.getLoggedInUser(); // 🔥 important

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());

        dto.setRiskType(user.getRiskType());

        dto.setImageData(user.getImageData());
        dto.setImageType(user.getImageType());
        dto.setImageName(user.getImageName());

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<?> uploadProfile(
            @RequestParam("file") MultipartFile file) throws IOException {

        User user = authUtil.getLoggedInUser();

        user.setImageData(file.getBytes());
        user.setImageType(file.getContentType());
        user.setImageName(file.getOriginalFilename());

        userRepository.save(user);

        return ResponseEntity.ok("Profile image updated");
    }




}
