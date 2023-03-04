package org.booking.dto;

import java.time.LocalDate;
import java.util.Objects;

public class BookingKey {
    private int roomNumber;
    private LocalDate bookingDate;

    public BookingKey(int roomNumber, LocalDate bookingDate) {
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingKey)) return false;
        BookingKey that = (BookingKey) o;
        return getRoomNumber() == that.getRoomNumber() && getBookingDate().equals(that.getBookingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getBookingDate());
    }
}
