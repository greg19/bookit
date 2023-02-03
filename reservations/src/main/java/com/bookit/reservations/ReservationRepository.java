package com.bookit.reservations;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findReservationsByCheckoutAfterAndCheckinBefore(Date start, Date end);
}
