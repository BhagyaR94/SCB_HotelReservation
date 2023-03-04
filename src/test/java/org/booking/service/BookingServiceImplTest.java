package org.booking.service;

import org.booking.exception.BookingException;
import org.booking.exception.RoomReservationException;
import org.booking.service.impl.BookingServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class BookingServiceImplTest {

    private BookingService bookingService;

    @Before
    public void init(){
        bookingService = new BookingServiceImpl();
    }

    @Test
    public void makeReservation_shouldCreateABookingWhenAllTheInputsAreValid(){
        String guestName = "Bhagya";
        int roomNumber = 101;
        LocalDate bookingDate = LocalDate.now();

        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        Assert.assertEquals(1, bookingService.getAllBookings().size());
    }

    @Test
    public void makeReservation_shouldCreateMultipleBookingsWhenAllTheInputsAreValid(){
        String guestName = "Bhagya";
        int roomNumber = 101;
        LocalDate bookingDate = LocalDate.now();
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        String guestTwoName = "Praveen";
        int roomTwoNumber = 102;
        LocalDate bookingTwoDate = LocalDate.now();
        bookingService.makeReservation(guestTwoName, roomTwoNumber, bookingTwoDate);

        Assert.assertEquals(2, bookingService.getAllBookings().size());
    }

    @Test
    public void findBookingByGuestName_shouldReturnAListOfBookingsPlacedByGuests(){
        LocalDate bookingDate = LocalDate.now();

        String guestName = "John";
        int roomNumber = 101;
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        String guest2Name = "Johanssen";
        int room2Number = 105;
        bookingService.makeReservation(guest2Name, room2Number, bookingDate);

        String guest3Name = "Joe";
        int room3Number = 102;
        bookingService.makeReservation(guest3Name, room3Number, bookingDate);

        String guest4Name = "Kevin";
        int room4Number = 201;
        bookingService.makeReservation(guest4Name, room4Number, bookingDate);

        String guest5Name = "Kiren";
        int room5Number = 303;
        bookingService.makeReservation(guest5Name, room5Number, bookingDate);

        String guest6Name = "Roy";
        int room6Number = 104;
        bookingService.makeReservation(guest6Name, room6Number, bookingDate);

        String guest7Name = "Sam";
        int room7Number = 205;
        bookingService.makeReservation(guest7Name, room7Number, bookingDate);

        Assert.assertEquals(3, bookingService.findBookingByGuestName("Jo").size());
        Assert.assertEquals(1, bookingService.findBookingByGuestName("Ke").size());
        Assert.assertEquals(1, bookingService.findBookingByGuestName("Roy").size());
        Assert.assertEquals(1, bookingService.findBookingByGuestName("Sam").size());
        Assert.assertEquals(0, bookingService.findBookingByGuestName("Chris").size());
    }

    @Test
    public void findBookingByDate_ShouldReturnTheBookingsForTheGivenDate(){
        LocalDate bookingDate = LocalDate.now().plusDays(5);
        LocalDate secondBookingDate = LocalDate.now().plusDays(7);

        String guestName = "John";
        int roomNumber = 101;
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        String guest2Name = "Johanssen";
        int room2Number = 105;
        bookingService.makeReservation(guest2Name, room2Number, bookingDate);

        String guest3Name = "Joe";
        int room3Number = 102;
        bookingService.makeReservation(guest3Name, room3Number, secondBookingDate);

        Assert.assertEquals(2, bookingService.findBookingByDate(bookingDate).size());
        Assert.assertEquals(1, bookingService.findBookingByDate(secondBookingDate).size());

    }

    @Test(expected = BookingException.class)
    public void makeReservation_shouldThrowBookingExceptionWhenTryingToBookARoomWhichIsAlreadyBooked(){

        LocalDate bookingDate = LocalDate.now();

        String guestName = "Bhagya";
        int roomNumber = 101;
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        String guestTwoName = "Praveen";
        int roomTwoNumber = 101;

        bookingService.makeReservation(guestTwoName, roomTwoNumber, bookingDate);

        Assert.assertEquals(1, bookingService.getAllBookings().size());
    }

    @Test(expected = BookingException.class)
    public void makeReservation_shouldThrowBookingExceptionWhenTryingToBookInThePast(){
        LocalDate bookingDate = LocalDate.now().minusDays(1);

        String guestName = "Bhagya";
        int roomNumber = 101;
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        Assert.assertEquals(0, bookingService.getAllBookings().size());
    }

    @Test(expected = RoomReservationException.class)
    public void makeReservation_shouldThrowRoomReservationExceptionWhenTryingToBookAnUnavailableRoom(){
        LocalDate bookingDate = LocalDate.now();

        String guestName = "Bhagya";
        int roomNumber = 601;
        bookingService.makeReservation(guestName, roomNumber, bookingDate);

        Assert.assertEquals(0, bookingService.getAllBookings().size());
    }

}
