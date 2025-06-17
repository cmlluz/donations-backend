package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dto.NeedDTO;
import com.model.Need;
import com.repository.NeedRepository;

@Service
public class NeedService {
    
    private final NeedRepository needRepository;

    public NeedService(NeedRepository needRepository){
        this.needRepository = needRepository;
    }

    public List<NeedDTO> listNeeds(){
        List<Need> needs = needRepository.findAll();
        return needs.stream().map(NeedDTO::new).toList();
    }

    public Optional<Need> searchById(Long id){
        return needRepository.findById(id);
    }

    public NeedDTO saveNeed(NeedDTO needDTO){
        Need need = new Need(needDTO);
        Need saved = needRepository.save(need);
        return new NeedDTO(saved);
    }

    public NeedDTO update(NeedDTO needDTO, Long id){
        Optional<Need> optionalNeed = searchById(id);
        if(optionalNeed.isEmpty()){
            return null;
        }
        Need need = optionalNeed.get();
        need.setDescription(needDTO.description());
        need.setDate(needDTO.date());
        need.setCategory(needDTO.category());
        need.setQuantity(needDTO.quantity());
        need.setStatus(needDTO.status());
        need.setFirebaseUserId(needDTO.firebaseUserId());

        Need updated = needRepository.save(need);
        return new NeedDTO(updated);
    }

    public boolean deleteNeed(Long id){
        Optional<Need> optionalNeed = searchById(id);
        if(optionalNeed.isEmpty()){
            return false;
        }
        needRepository.deleteById(id);
        return true;
    }
}
