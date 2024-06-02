package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SearchListofBusesDto;
import com.reservationapp.repository.BusRepository;

import com.reservationapp.repository.RouteRepository;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @PostMapping("/add")
    public ResponseEntity<Bus> addBus( @RequestBody BusDto busDto)  throws ParseException{
       Bus bus= busService.addBus(busDto);
        return  new ResponseEntity<>(bus,HttpStatus.CREATED);
    }
    @GetMapping
    public List<SearchListofBusesDto> getAllBuses(@RequestParam String fromLocation,
                                                  @RequestParam String toLocation,
                                                  @RequestParam String fromDate) {

       List<Route> routes= routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,toLocation,fromDate);
        List<SearchListofBusesDto> buses =new ArrayList<>();
       for(Route route : routes) {
          Bus bus =busRepository.findById(route.getBusId()).get();
           SearchListofBusesDto searchListofBusesDto =mapToSearchListofBuses(bus,route);
           buses.add(searchListofBusesDto);
       }
            return buses;

    }

//    this is for Route Same thing for SubRoute.

    SearchListofBusesDto mapToSearchListofBuses(Bus bus,Route route){
        SearchListofBusesDto searchListofBusesDto = new SearchListofBusesDto();
        searchListofBusesDto.setBusId(bus.getBusId());
        searchListofBusesDto.setBusNumber(bus.getBusNumber());
        searchListofBusesDto.setBusType(bus.getBusType());
        searchListofBusesDto.setPrice(bus.getPrice());
        searchListofBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListofBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListofBusesDto.setRouteId(bus.getRouteId());
        searchListofBusesDto.setFromLocation(bus.getFromLocation());
        searchListofBusesDto.setToLocation(bus.getToLocation());
        searchListofBusesDto.setFromDate(bus.getFromDate());
        searchListofBusesDto.setToDate(bus.getToDate());
        searchListofBusesDto.setTotalDuration(bus.getTotalDuration());
        searchListofBusesDto.setFromTime(bus.getFromTime());
        searchListofBusesDto.setBusId(route.getBusId());
        searchListofBusesDto.setBusNumber(route.getBusNumber());
        searchListofBusesDto.setBusType(route.getBusType());
        searchListofBusesDto.setPrice(route.getPrice());
        searchListofBusesDto.setTotalSeats(route.getTotalSeats());
        searchListofBusesDto.setAvailableSeats(route.getAvailableSeats());
        searchListofBusesDto.setRouteId(route.getRouteId());
        searchListofBusesDto.setFromLocation(route.getFromLocation());
        searchListofBusesDto.setToLocation(route.getToLocation());
        searchListofBusesDto.setFromDate(route.getFromDate());
        searchListofBusesDto.setToDate(route.getToDate());
        searchListofBusesDto.setTotalDuration(route.getTotalDuration());
        searchListofBusesDto.setFromTime(route.getFromTime());

        return  searchListofBusesDto;

    }

    }


