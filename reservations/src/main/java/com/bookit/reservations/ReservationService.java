package com.bookit.reservations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public String addReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setPaid(false);
        return reservationRepository.save(reservation).getId();
    }

    // Get list of ids of offers which are booked during time period between start and end
    public List<String> searchReservations(Date start, Date end) {
        return reservationRepository.findReservationsByCheckoutAfterAndCheckinBefore(start, end)
                .stream().map((Reservation::getOfferId)).distinct().collect(Collectors.toList());
    }

    public boolean getStatus(String id) {
        return reservationRepository.findById(id).orElseThrow(NoSuchElementException::new).isPaid();
    }

    public Reservation getReservation(String id) {
        return reservationRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void markAsPaid(String id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        reservation.setPaid(true);
        reservationRepository.save(reservation);
    }
}
