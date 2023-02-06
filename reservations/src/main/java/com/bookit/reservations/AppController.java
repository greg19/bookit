package com.bookit.reservations;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Log4j2
@Controller
public class AppController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private Environment env;

    @PostMapping("/")
    public RedirectView addReservation(ReservationDTO reservationDTO) {
        log.info("/");
        String id = reservationService.addReservation(reservationDTO);
        return new RedirectView("/status/" + id);
    }

    @GetMapping("/paid/{id}")
    public ResponseEntity<?> markAsPaid(@PathVariable String id) {
        log.info("/paid/id");
        reservationService.markAsPaid(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{id}")
    public String getStatus(@PathVariable String id, Model model) {
        log.info("/status/id");
        model.addAttribute("isPaid", reservationService.getStatus(id));
        model.addAttribute("reservation", reservationService.getReservation(id));
        model.addAttribute("payUrl", env.getProperty("payments.url.pay"));
        return "status";
    }

    // Get list of ids of offers which are booked during time period between checkin and checkout
    @GetMapping("/search")
    public ResponseEntity<?> searchReservations(@RequestParam
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,
                                                @RequestParam
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        log.info("/search");
        return ResponseEntity.ok(reservationService.searchReservations(checkin, checkout));
    }

}
