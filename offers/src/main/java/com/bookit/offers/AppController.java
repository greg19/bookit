package com.bookit.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AppController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String offers(Model model) {
        model.addAttribute("offer", new OfferDTO());
        return "index";
    }
    @GetMapping("/search")
    public String searchOffers(@RequestParam(value = "checkin")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,
                                          @RequestParam(value = "checkout")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout,
                                          Model model) {
        model.addAttribute("offers", offerService.searchOffers(checkin, checkout));
        return "offers-list";
    }

    @GetMapping("/offer/{id}")
    public String getOffer(@PathVariable String id, Model model) {
        model.addAttribute("offer", offerService.getOffer(id));
        model.addAttribute("reservationDTO", new ReservationDTO());
        model.addAttribute("addReservation", env.getProperty("reservations.url.add"));
        return "single-offer";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute OfferDTO offer) {
        offerService.addOffer(offer);
        return "offer-added";
    }
}
