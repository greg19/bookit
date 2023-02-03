package com.bookit.reservations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public String addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        return reservationRepository.save(reservation).getId();
    }

    // Get list of ids of offers which are booked during time period between start and end
    public List<String> searchReservations(Date start, Date end) {
        return reservationRepository.findReservationsByCheckoutAfterAndCheckinBefore(start, end)
                .stream().map((Reservation::getOfferId)).distinct().collect(Collectors.toList());
    }
}
