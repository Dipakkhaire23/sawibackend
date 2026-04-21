package com.sawi_health.demo.controller;




import com.sawi_health.demo.config.JwtUtil;
import com.sawi_health.demo.dto.*;
import com.sawi_health.demo.model.User;
import com.sawi_health.demo.repository.UserRepository;
import com.sawi_health.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/health")
    @ResponseBody
    public  String healthcheack(){
        return "Backend Deployed Successfully!!";
    }


    @PostMapping("/register")

    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest, HttpServletResponse response) {
        try {
            UserDto userDto = userService.createUser(userRequest);
            String token = jwtUtil.generateToken(userDto.getEmail(),userDto.getRole());
            AuthResponse authResponse = new AuthResponse( token,userDto.getId(),userDto.getName(),userDto.getRole());


            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("User not found.");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userOptional.get();
            String token = jwtUtil.generateToken(userDetails.getUsername(),user.getRole());
            return ResponseEntity.ok(new AuthResponse(token,user.getId(),user.getName(),user.getRole() ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials." +e.getMessage());
        }
    }


}
