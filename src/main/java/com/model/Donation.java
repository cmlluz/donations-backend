package com.model;

import java.time.LocalDate;

import com.dto.DonationDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "donations")
public class Donation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String firebaseUserId;

    public Donation() {}

    public Donation(Long id, String description, LocalDate date, Category category, int quantity, Status status, String firebaseUserId) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.category = category;
        this.quantity = quantity;
        this.status = status;
        this.firebaseUserId = firebaseUserId;
    }

    public Donation(DonationDTO donationDTO) {
        this.id = donationDTO.id();
        this.description = donationDTO.description();
        this.date = donationDTO.date();
        this.category = donationDTO.category();
        this.quantity = donationDTO.quantity();
        this.status = donationDTO.status();
        this.firebaseUserId = donationDTO.firebaseUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {  
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }
}
