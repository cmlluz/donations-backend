package com.dto;

import java.time.LocalDateTime;

import com.model.Post;

public record PostDTO(
    Long id,
    LocalDateTime createdAt,
    String authorUid,
    String imageUrl,
    String caption,
    boolean favorited
) {
    public PostDTO(Post post) {
        this(
            post.getId(),
            post.getCreatedAt(),
            post.getAuthorUid(),
            post.getImageUrl(),
            post.getCaption(),
            post.isFavorited()
        );
    }
}
