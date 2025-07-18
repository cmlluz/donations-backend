package com.controller;

import com.model.User;
import com.model.UserRole;
import com.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sync")
    public ResponseEntity<User> syncUser(Principal principal) {
        String uid = principal.getName();
        return userRepository.findById(uid)
            .map(ResponseEntity::ok)
            .orElseGet(() -> {
                try {
                    UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
                    User newUser = new User();

                    // --- INÍCIO DA CORREÇÃO ---
                    // Verifica se o nome de exibição existe. Se não, usa o email.
                    String displayName = userRecord.getDisplayName();
                    if (displayName == null || displayName.isBlank()) {
                        // Se o email também não existir (improvável), usa uma string genérica
                        displayName = userRecord.getEmail() != null ? userRecord.getEmail() : "Novo Usuário";
                    }
                    newUser.setName(displayName);
                    // --- FIM DA CORREÇÃO ---
                    
                    newUser.setUid(uid);
                    newUser.setEmail(userRecord.getEmail());
                    newUser.setProfilePictureUrl(userRecord.getPhotoUrl());
                    newUser.setRole(UserRole.ROLE_USER);

                    User savedUser = userRepository.save(newUser);
                    return ResponseEntity.ok(savedUser);

                } catch (FirebaseAuthException e) {
                    // Logar o erro pode ser útil aqui para debug
                    e.printStackTrace();
                    return ResponseEntity.status(500).build();
                }
            });
    }
}