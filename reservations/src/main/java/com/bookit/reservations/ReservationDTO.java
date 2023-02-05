package com.bookit.reservations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private String id;

    private String offerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout;

    private String email;
}
