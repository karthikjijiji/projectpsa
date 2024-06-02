package com.reservationapp.payload;

import lombok.Data;

@Data
public class SearchListofBusesDto {

    private  Long BusId;

    private String busNumber;
    private String busType;

    private double price;
    private int totalSeats;
    private int availableSeats;

    private Long routeid;

    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;

}
