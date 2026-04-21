package com.sawi_health.demo.controller;




import com.sawi_health.demo.dto.ConsultationDto;
import com.sawi_health.demo.dto.MeetingDto;
import com.sawi_health.demo.dto.UserDto;
import com.sawi_health.demo.model.Consultation;
import com.sawi_health.demo.service.MeetingService;
import com.sawi_health.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserService userService;


    @Autowired
    private MeetingService service;

    @GetMapping("/allUsers")
    public List<UserDto> getalluser(){
        return userService.getAllUsers();
    }
//    // ✅ 2. Get user by ID
//
    // ✅ 4. Delete any user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }

    // 🔥 ADMIN CREATE
    @PostMapping("/meetings")
    public ResponseEntity<MeetingDto> create(@RequestBody MeetingDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // 🔥 USER VIEW
    @GetMapping("/meetings")
    public ResponseEntity<List<MeetingDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    // 🔥 USER SUBMIT
    @PostMapping("/consultation")
    public ResponseEntity<String> save(@RequestBody ConsultationDto dto) {
        service.save(dto);
        return ResponseEntity.ok("Saved");
    }

    // 🔥 ADMIN VIEW
    @GetMapping("/consultations")
    public List<Consultation> getAllconsultation() {
        return service.getAllConsultation();
    }
}
