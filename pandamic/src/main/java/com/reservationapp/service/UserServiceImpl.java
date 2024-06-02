package com.reservationapp.service;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public void createUser(UserRegistration userRegistration) {
        userRegistrationRepository.save(userRegistration);
    }

    public UserRegistration getUserById(long id) {
        return userRegistrationRepository.findById(id).orElse(null);
    }

    public void deleteUserById(long id) {
        userRegistrationRepository.deleteById(id);
    }
    public void updateUserById(long id, UserRegistration userRegistration) {
        Optional<UserRegistration> existingUserOptional = userRegistrationRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            UserRegistration existingUser = existingUserOptional.get();
            // Update existing user details
            existingUser.setName(userRegistration.getName());
            existingUser.setEmail(userRegistration.getEmail());
            existingUser.setPassword(userRegistration.getPassword());
            // Save the updated user
            userRegistrationRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }



}
