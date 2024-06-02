package com.reservationapp.controller;


import com.reservationapp.payload.JWTResponse;
import com.reservationapp.payload.LoginDto;
import com.reservationapp.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


    //http://localhost:8080/api/v1/users/addUser
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto dto) {
        PropertyUser user = userService.addUser(dto);
        if (user != null) {
            return new ResponseEntity<>("sign up Successsful", HttpStatusCode.Created);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatusCode.InternalServerError);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto) {
        String JWTToken = userService.verifyLogin(LoginDto);
        if (JWTToken != null) {
            JWTResponse jwtResponse = new JWTResponse();
            jwtResponse.setToken(JWTToken);
            return new ResponseEntity<>(jwtResponse, HttpStatusCode.OK);
        }
        return new ResponseEntity<>("invalid credentials ", HttpStatusCode.UNAUTHORIZED);
    }


    @GetMapping("/Profile")
    public PropertyUser getCurrentProfile(@AuthenticationPrincipal PropertyUser propertyUser) {
        return propertyUser;


    }
}






















