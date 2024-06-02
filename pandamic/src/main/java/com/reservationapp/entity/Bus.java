package com.reservationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    private String busNumber;
    private String busType;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private int totalDuration;
    private String fromTime;
    private String toTime;
    private double price;
    private int totalSeats;
    private int availableSeats;

    @OneToOne(mappedBy ="bus")
    private  Route  route;
}
