package com.bookit.offers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfferDTO {

    private String id;

    private String address;

    private String description;

    private String email;
}
