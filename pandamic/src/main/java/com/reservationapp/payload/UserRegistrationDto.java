package com.reservationapp.payload;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private long id;
    private String name;
    private String email;
    private String password;
}
