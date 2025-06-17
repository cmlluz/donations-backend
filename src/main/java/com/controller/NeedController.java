package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dto.NeedDTO;
import com.model.Need;
import com.service.NeedService;

@RestController
@RequestMapping("/api/needs")
public class NeedController {
    
    private final NeedService needService;

    public NeedController(NeedService needService){
        this.needService = needService;
    }

    @GetMapping
    public ResponseEntity<List<NeedDTO>> listNeeds(){
       List<NeedDTO> needs = needService.listNeeds();
       return ResponseEntity.ok(needs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeedDTO> searchNeed(@PathVariable Long id){
        return needService.searchById(id)
            .map(need -> ResponseEntity.ok(new NeedDTO(need)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NeedDTO> saveNeed(@RequestBody NeedDTO needDTO){
        NeedDTO saved = needService.saveNeed(needDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NeedDTO> update(@RequestBody NeedDTO needDTO, @PathVariable Long id){
        NeedDTO updated = needService.update(needDTO, id);
        if(updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id){
        boolean deleted = needService.deleteNeed(id);
        if(deleted)
           return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
