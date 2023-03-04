package org.booking.service;

import org.booking.dto.BookingDTO;
import org.booking.exception.BookingException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingService {
    List<BookingDTO> findBookingByGuestName(String guestNames);

    void makeReservation(String guestName, int roomNumber, LocalDate bookingDate) throws BookingException;

    List<BookingDTO> getAllBookings();
}
