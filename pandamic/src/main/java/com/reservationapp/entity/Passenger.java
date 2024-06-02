package com.reservationapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String FirstName;
    private String LastName;
    private String email;
    private long mobile;
    private long buId;
    private long routeId;

}
