package com.bookit.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AppController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOffer(@PathVariable String id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchOffers(@RequestParam
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkin,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkout) {
        return ResponseEntity.ok(offerService.searchOffers(checkin, checkout));
    }

    @PostMapping("/")
    public ResponseEntity<?> addOffer(@RequestBody OfferDTO offerDTO) {
        return ResponseEntity.ok(offerService.addOffer(offerDTO));
    }
}
