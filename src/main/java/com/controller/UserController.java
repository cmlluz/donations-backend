package com.controller;

import com.dto.PublicUserDTO;
import com.dto.UserDTO;
import com.model.User;
import com.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> getMyProfile(Principal principal) {
        String uid = principal.getName();
        return userService.getUserByUid(uid)
                .map(user -> ResponseEntity.ok(new UserDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> updateMyProfile(@RequestBody User userDetails, Principal principal) {
        String uid = principal.getName();
        User updatedUser = userService.updateUser(uid, userDetails);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDTO(updatedUser));
    }

    @GetMapping("/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PublicUserDTO> getUserProfile(@PathVariable String uid) {
        return userService.getUserByUid(uid)
                .map(user -> ResponseEntity.ok(new PublicUserDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}