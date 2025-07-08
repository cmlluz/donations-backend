package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {
    
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUid(String uid) {
        return userRepository.findById(uid);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String uid, User userDetails) {
        User user = userRepository.findById(uid).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setAdress(userDetails.getAdress());
            user.setPhone(userDetails.getPhone());
            user.setCpfOrCnpj(userDetails.getCpfOrCnpj());
            user.setProfilePictureUrl(userDetails.getProfilePictureUrl());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(String uid){
        Optional<User> optionalUser = getUserByUid(uid);
        if(optionalUser.isEmpty()){
            return false;
        }
        userRepository.deleteById(uid);
        return true;
    }
}
