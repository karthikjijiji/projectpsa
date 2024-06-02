package com.reservationapp.controller;


import com.reservationapp.entity.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {

    @PostMapping("/{busId")
    public ResponseEntity<Route> addRoute(@PathVariable long busId, @RequestBody Route route){
        Route r =routeService.createRoute(busId,route);
        return new ResponseEntity<>(r,HttpStatus.created);
    }

}
