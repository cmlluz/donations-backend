package com.dto;

import com.model.User;
import com.model.UserRole;

public record UserDTO(
    String uid,
    String name,
    String email,
    UserRole role,
    String password,
    String phone,
    String adress,
    String cpfOrCnpj, 
    String profilePictureUrl
) {
    public UserDTO (User user) {
        this(
            user.getUid(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getPassword(),
            user.getPhone(),
            user.getAdress(),
            user.getCpfOrCnpj(),
            user.getProfilePictureUrl()
        );
    }
    
}
