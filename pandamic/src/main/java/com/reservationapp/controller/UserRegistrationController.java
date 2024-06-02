package com.reservationapp.controller;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRegistration userRegistration) {
        userService.createUser(userRegistration);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRegistration> getUserById(@PathVariable long id) {
        UserRegistration user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UserRegistration userRegistration) {
        try {
            userService.updateUserById(id, userRegistration);
            return new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Record not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
