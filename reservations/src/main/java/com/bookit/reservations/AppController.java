package com.bookit.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AppController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<?> addReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.addReservation(reservationDTO));
    }

    // Get list of ids of offers which are booked during time period between checkin and checkout
    @GetMapping("/search")
    public ResponseEntity<?> searchReservations(@RequestParam
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,
                                                @RequestParam
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        return ResponseEntity.ok(reservationService.searchReservations(checkin, checkout));
    }

}
