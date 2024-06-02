package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.RouteDto;
import com.reservationapp.payload.SubRouteDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.DriverRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  BusService {

    @Autowired
    private BusRepository busRepository;
    @Autowired  
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Transactional
    public Bus addBus(BusDto busDto) {
        Bus bus = new Bus();
          bus.setBusNumber(busDto.getBusNumber());
          bus.setBusType(busDto.getBusType());
          bus.setPrice(busDto.getPrice());
          bus.setTotalSeats(busDto.getTotalSeats());
          bus.setAvailableSeats(busDto.getAvailableSeats());

        Bus savedBus =busRepository.save(bus);
        return savedBus;
    }
}
