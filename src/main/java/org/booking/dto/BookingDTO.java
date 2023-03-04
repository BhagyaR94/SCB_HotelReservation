package org.booking.dto;

import java.time.LocalDate;

public class BookingDTO {
    private String guestName;
    private int roomNumber;

    private LocalDate bookingDate;

    public BookingDTO(String guestName, int roomNumber, LocalDate bookingDate) {
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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
