package com.reservationapp.service;

import com.reservationapp.payload.LoginDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropertyUserRepository propertyUserRepository;

    private JWTService jwtService;

    public UserService(PropertyUserRepository userRepository, JWTService jwtService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;


        public PropertyUser addUser(PropertyUserDto  propertyUserDto){
            PropertyUser user = new PropertyUser();
            user.setFirstName(propertyUserDto.getFirstName());
            user.setLastname(propertyUserDto.getLastname());
            user.setUsername(propertyUserDto.getUsername());
            user.setUserRole(propertyUserDto.getUserRole());
            user.setEmail(propertyUserDto.getEmail());
            user.setPassword(Bcrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt(12));
            // Optionally, set any other properties if needed

            userRepository.save(user);
            return user;
        }

    }
    }

    public String verifyLogin(LoginDto loginDto){
     Optional<PropertyUser> opUser=userRepository.findByUsername(loginDto.getUsername());
     if(opUser.isPresent()){
         PropertyUser user = opUser.get();
         if( BCrypt.checkpw(loginDto.getPassword(),user.getPasssword()){
             return jwtService.generateToken(user);

         }

     }
     return null;
}
