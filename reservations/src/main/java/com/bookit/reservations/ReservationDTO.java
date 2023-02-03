package com.bookit.reservations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private String id;

    private String offerId;

    private Date checkin;

    private Date checkout;
}
