package org.booking.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class BookingDTO {
    private String guestName;
    private int roomNumber;

    private LocalDateTime bookingDate;

    public BookingDTO(String guestName, int roomNumber, LocalDateTime bookingDate) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
