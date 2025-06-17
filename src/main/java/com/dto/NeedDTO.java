package com.dto;

import java.time.LocalDate;

import com.model.Category;
import com.model.Need;
import com.model.Status;

public record NeedDTO(
    Long id,
    String description,
    LocalDate date,
    Category category,
    int quantity,
    Status status,
    String firebaseUserId
) {
    public NeedDTO(Need need){
        this(
            need.getId(),
            need.getDescription(),
            need.getDate(),
            need.getCategory(),
            need.getQuantity(),
            need.getStatus(),
            need.getFirebaseUserId()
        );
    }
}
