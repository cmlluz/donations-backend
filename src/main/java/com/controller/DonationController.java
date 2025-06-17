package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.DonationDTO;
import com.model.Donation;
import com.service.DonationService;

@RestController
@RequestMapping("/api/donations")
public class DonationController {
    
    private final DonationService donationService;

    public DonationController(DonationService donationService){
        this.donationService = donationService;
    }

    @GetMapping
    public ResponseEntity<List<DonationDTO>> listDonations(){
       List<DonationDTO> donations = donationService.listDonations();
       return ResponseEntity.ok(donations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationDTO> searchDonation(@PathVariable Long id){
        return donationService.searchById(id)
            .map(donation -> ResponseEntity.ok(new DonationDTO(donation)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DonationDTO> saveDonation(@RequestBody DonationDTO donationDTO){
        DonationDTO donationSave =  donationService.saveDonation(donationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(donationSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationDTO> update(@RequestBody DonationDTO donationDTO, @PathVariable Long id){
        DonationDTO donationUpdate = donationService.update(donationDTO, id);
        if(donationUpdate == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(donationUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id){
        boolean deleted = donationService.deleteDonation(id);
        if(deleted)
           return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
