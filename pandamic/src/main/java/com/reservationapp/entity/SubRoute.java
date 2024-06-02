package com.reservationapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubRoute {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fromLocation;
        private String toLocation;
        private String fromDate;
        private String toDate;
        private String totalDuration;
        private String fromTime;
        private String toTime;

        @ManyToOne
        @JoinColumn(name = "route_id")
        private Route route;
}
