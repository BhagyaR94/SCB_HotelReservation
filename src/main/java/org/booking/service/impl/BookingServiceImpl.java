package org.booking.service.impl;

import org.booking.dto.BookingDTO;
import org.booking.dto.BookingKey;
import org.booking.exception.BookingException;
import org.booking.service.BookingService;
import org.booking.service.RoomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    private final Map<BookingKey, BookingDTO> bookings;
    private final RoomService roomService;

    public BookingServiceImpl() {
        this.bookings = new Hashtable<>();
        this.roomService = new RoomServiceImpl();
    }

    public void makeReservation(String guestName, int roomNumber, LocalDate bookingDate) throws BookingException {
        LocalDate formattedBookingDate = getFormattedBookingDate(bookingDate);
        BookingKey bookingKey = getBookingKey(roomNumber, formattedBookingDate);
        BookingDTO bookingValue = getBookingDTO(guestName, roomNumber, formattedBookingDate);

        if (bookings.containsKey(bookingKey)) {
            throw new BookingException("Selected Room Already Allocated On The Selected Date");
        } else {
            if (roomService.isRoomBookable(roomNumber)) {
                bookings.put(bookingKey, bookingValue);
                roomService.removeFromAvailable(roomNumber);
            }
        }
    }

    public List<BookingDTO> findBookingByGuestName(String guestName) {
        Predicate<BookingDTO> filterByGuestName = bookingDTO -> bookingDTO.getGuestName().contains(guestName);
        return bookings.values().stream().filter(filterByGuestName).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> findBookingByDate(LocalDate bookingDate) {
        Predicate<BookingDTO> filterByBookingDate = bookingDTO -> bookingDTO.getBookingDate().equals(bookingDate);
        return bookings.values().stream().filter(filterByBookingDate).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }

    private LocalDate getFormattedBookingDate(LocalDate bookingDate) {

        if (bookingDate.isBefore(LocalDate.now())) {
            throw new BookingException("Booking Date Cannot Be In The Past");
        }

        return bookingDate;
    }

    private BookingDTO getBookingDTO(String guestName, int roomNumber, LocalDate bookingDate) {
        return new BookingDTO(guestName, roomNumber, bookingDate);
    }

    private BookingKey getBookingKey(int roomNumber, LocalDate bookingDate) {
        return new BookingKey(roomNumber, bookingDate);
    }
}
