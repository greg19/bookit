package com.bookit.offers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@Controller
public class AppController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String offers(Model model) {
        log.info("/");
        model.addAttribute("offer", new OfferDTO());
        return "index";
    }
    @GetMapping("/search")
    public String searchOffers(@RequestParam(value = "checkin")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,
                                          @RequestParam(value = "checkout")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout,
                                          Model model) {
        log.info("/search");
        model.addAttribute("offers", offerService.searchOffers(checkin, checkout));
        return "offers-list";
    }

    @GetMapping("/offer/{id}")
    public String getOffer(@PathVariable String id, Model model) {
        log.info("/offer/id");
        model.addAttribute("offer", offerService.getOffer(id));
        model.addAttribute("reservationDTO", new ReservationDTO());
        model.addAttribute("addReservation", env.getProperty("reservations.url.add"));
        return "single-offer";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers() {
        log.info("/all");
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute OfferDTO offer) {
        log.info("/add");
        offerService.addOffer(offer);
        return "offer-added";
    }
}
