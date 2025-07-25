package com.dto;

import com.model.User;
import com.model.UserRole;

public record PublicUserDTO(
    String uid,
    String name,
    UserRole role,
    String email,
    String profilePictureUrl
) {
    public PublicUserDTO(User user) {
        this(
            user.getUid(),
            user.getName(),
            user.getRole(),
            user.getEmail(),
            user.getProfilePictureUrl()
        );
    }
}
