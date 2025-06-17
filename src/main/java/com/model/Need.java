package com.model;

import java.time.LocalDate;

import com.dto.NeedDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "needs")
public class Need {
    
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

    public Need(){}

    public Need(Long id, String description, LocalDate date, Category category, int quantity){
        this.id = id;
        this.description = description;
        this.date = date;
        this.category = category;
        this.quantity = quantity;
    }

    public Need(NeedDTO needDTO){
        this.id = needDTO.id();
        this.description = needDTO.description();
        this.date = needDTO.date();
        this.category = needDTO.category();
        this.quantity = needDTO.quantity();
        this.status = needDTO.status();
        this.firebaseUserId = needDTO.firebaseUserId();
    }

    // getters e setters

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }

    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    public String getFirebaseUserId(){
        return firebaseUserId;
    }
    public void setFirebaseUserId(String firebaseUserId){
        this.firebaseUserId = firebaseUserId;
    }
}
