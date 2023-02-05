package com.bookit.reservations;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    private String id;

    @NonNull
    private String offerId;

    @NonNull
    private boolean isPaid;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkout;

    @NonNull
    private String email;
}
