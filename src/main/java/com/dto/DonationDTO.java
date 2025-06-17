package com.dto;

import java.time.LocalDate;

import com.model.Category;
import com.model.Donation;
import com.model.Status;

public record DonationDTO(
    Long id,
    String description,
    LocalDate date,
    Category category,
    int quantity,
    Status status,
    String firebaseUserId
) {
    public DonationDTO(Donation donation) {
        this(
            donation.getId(),
            donation.getDescription(),
            donation.getDate(),
            donation.getCategory(),
            donation.getQuantity(),
            donation.getStatus(),
            donation.getFirebaseUserId()
        );
    }
}
