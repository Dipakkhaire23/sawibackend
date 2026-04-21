package com.sawi_health.demo.service;



import com.sawi_health.demo.config.AuthUtil;
import com.sawi_health.demo.dto.*;
import com.sawi_health.demo.model.Role;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.InvestmentRepository;
import com.sawi_health.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InvestmentRepository investmentRepository;



    @Value("${frontend.url1}")
    private String FrontendUrl;


    public UserDto createUser(UserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhoneNumber(request.getPhoneNumber());

        user.setRole(Role.CITIZEN);




        return UserMapper.toDto(userRepository.save(user));
    }




    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == Role.CITIZEN)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }


    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDto(user);
    }


    public List<UserDto> getUserByName(String name) {
        return userRepository.findByName(name).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }




    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        investmentRepository.deleteByUserId(id); // 🔥 first delete child
        userRepository.delete(user);
    }









    public UserDto getCurrentUser() {
        User user = authUtil.getLoggedInUser();
        return UserMapper.toDto(user);
    }

}
