package com.dto;

import java.time.LocalDateTime;

import com.model.Post;

public record PostDTO(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    String authorUid
) {
    public PostDTO(Post post) {
        this(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getCreatedAt(),
            post.getAuthorUid()
        );
    }
}
