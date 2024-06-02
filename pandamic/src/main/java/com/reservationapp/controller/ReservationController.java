package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Passenger;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.PassengerRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.ExcelGeneratorService;
import com.reservationapp.util.PdfTicketGeneratorService;
import com.reservationapp.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {


    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExcelGeneratorService excelGeneratorService;
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestParam long busId,
                                             @RequestParam long routeId,
                                             @RequestParam String seatNumber,
                                             @RequestBody Passenger passenger) {
        boolean busIsPresent = false;
        boolean routeIsPresent = false;
        boolean subRouteIsPresent = false;

        Optional<Bus> optionalBus = busRepository.findById(busId);
        if (optionalBus.isPresent()) {
            busIsPresent = true;
            Bus bus = optionalBus.get();
        }

        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            routeIsPresent = true;
            Route route = optionalRoute.get();
        }

        Optional<SubRoute> optionalSubRoute = subRouteRepository.findById(routeId);
        if (optionalSubRoute.isPresent()) {
            subRouteIsPresent = true;
            SubRoute subRoute = optionalSubRoute.get();
        }

        if ((busIsPresent && routeIsPresent) || (busIsPresent && subRouteIsPresent)) {
            Passenger p = new passenger();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setMobile(passenger.getMobile());
            p.setRouteId(passenger.getrouteId());
            p.setBusId(passenger.getbusId)
            passenger savedPassenger = passengerRepository.save(p);

            byte[] pdfBytes = pdfTicketGeneratorService.generatePdf(savedPassenger,byRouteId.get().getFromLocation(),byRouteId.get().getToLocation(),byRouteId.get().getFromDate());
            emailService.sendEmailWithAttachment(passenger.getEmail(), "Booking Confirmed", "Your reservation details are attached.", pdfBytes);
            return ResponseEntity.ok("Booking Confirmed and email sent!");
        }

        return ResponseEntity.badRequest().body("Invalid bus or route/sub-route.");
    }



    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateExcel() {
        List<Passenger>passengers =fetchPassengersFromDatabase();
        byte[] excelBytes = excelGeneratorService.generateExcel(passenger);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "passenger_data.xlsx");

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
}
private List<Passenger> fetchPassengersFromDatabase(){
    return  PassengerRepository.findAll()
}
