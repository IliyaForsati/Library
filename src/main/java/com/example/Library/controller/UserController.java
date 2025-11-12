package com.example.Library.controller;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.UserDTO;
import com.example.Library.model.enums.UserRole;
import com.example.Library.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// </editor-fold>

@RestController
public class UserController {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private UserService userService;
    @Autowired private ModelMapper mapper;
    // </editor-fold>

    // <editor-fold desc="End Points">
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DTO.UserRecord userRecord) {
        UserDTO dto = mapper.map(userRecord, UserDTO.class);
        if (userService.existByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        dto.setRole(UserRole.USER);
        userService.add(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/api/users/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/api/users/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @RequestParam UserRole role) {
        userService.assignRole(id, role);
        return ResponseEntity.ok("Role updated successfully");
    }

    @PutMapping("/api/users/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        boolean changed = userService.changePassword(id, oldPassword, newPassword);
        if (changed) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }
    }
    // </editor-fold>
}
