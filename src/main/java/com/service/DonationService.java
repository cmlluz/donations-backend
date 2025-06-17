package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.DonationDTO;
import com.model.Donation;
import com.repository.DonationRepository;

@Service
public class DonationService {
    
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository){
        this.donationRepository = donationRepository;
    }

    public List<DonationDTO> listDonations(){
        List<Donation> donations = donationRepository.findAll();
        return donations.stream()
                        .map(DonationDTO::new)
                        .toList();
    }

    public Optional<Donation> searchById(Long id){
        return donationRepository.findById(id);
    }

    public DonationDTO update(DonationDTO donationDTO, Long id){
        Optional<Donation> optionalDonation = searchById(id);
        if(optionalDonation.isEmpty()){
            return null;
        }
        Donation donation = optionalDonation.get();
        donation.setDescription(donationDTO.description());
        donation.setDate(donationDTO.date());
        donation.setCategory(donationDTO.category());
        donation.setQuantity(donationDTO.quantity());
        donation.setStatus(donationDTO.status());
        donation.setFirebaseUserId(donationDTO.firebaseUserId());

        Donation updatedDonation = donationRepository.save(donation);
        return new DonationDTO(updatedDonation);
    }

    public DonationDTO saveDonation(DonationDTO donationDTO){
        Donation donation = new Donation(donationDTO);
        Donation savedDonation = donationRepository.save(donation);
        return new DonationDTO(savedDonation);
    }

    public boolean deleteDonation(Long id){
        Optional<Donation> optionalDonation = searchById(id);
        if(optionalDonation.isEmpty()){
            return false;
        }
        donationRepository.deleteById(id);
        return true;
    }
}
