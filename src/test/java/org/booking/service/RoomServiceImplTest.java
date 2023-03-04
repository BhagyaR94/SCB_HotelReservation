package org.booking.service;

import org.booking.exception.RoomReservationException;
import org.booking.service.impl.RoomServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomServiceImplTest {

    private RoomService roomService;

    @Before
    public void init() {
        roomService = new RoomServiceImpl();
    }

    @Test
    public void findAvailableRooms_ShouldReturnAllTheAvailableRooms() {
        Assert.assertEquals(15, roomService.findAvailableRooms().size());
    }

    @Test
    public void isRoomAvailable_shouldReturnTrueWhenTheGivenRoomNumberIsAvailable() {
        Assert.assertTrue(roomService.isRoomAvailable(101));
    }

    @Test
    public void isRoomAvailable_shouldReturnFalseWhenTheGivenRoomNumberIsNotAvailable() {
        Assert.assertFalse(roomService.isRoomAvailable(501));
    }

    @Test
    public void isValidRoomNumber_ShouldReturnTrueWhenProvidedRoomNumberIsValid() {
        Assert.assertTrue(roomService.isValidRoomNumber(101));
    }

    @Test
    public void isValidRoomNumber_ShouldReturnFalseWhenProvidedRoomNumberIsValid() {
        Assert.assertFalse(roomService.isValidRoomNumber(501));
    }

    @Test
    public void removeFromAvailable_shouldRemoveRoomFromAvailableList() {
        roomService.removeFromAvailable(101);
        Assert.assertEquals(14, roomService.findAvailableRooms().size());
    }

    @Test
    public void isRoomBookable_ShouldReturnTrueWhenRoomIsBookable() {
        Assert.assertTrue(roomService.isRoomBookable(101));
    }

    @Test
    public void addNewRoom_ShouldAddNewRoomToTheExistingRoomSet() {
        roomService.addNewRoom(505);
        Assert.assertEquals(16, roomService.findAvailableRooms().size());
    }

    @Test(expected = RoomReservationException.class)
    public void addNewRoom_ShouldThrowRoomReservationExceptionWhenTheRoomIsAlreadyAvailable() {
        roomService.addNewRoom(101);
    }

    @Test(expected = RoomReservationException.class)
    public void isRoomBookable_ShouldThrowRoomServiceExceptionWhenSelectedRoomIsInvalid() {
        roomService.isRoomBookable(501);
    }

    @Test(expected = RoomReservationException.class)
    public void isRoomBookable_ShouldThrowRoomServiceExceptionWhenSelectedRoomIsNotAvailable() {
        roomService.removeFromAvailable(101);
        roomService.isRoomBookable(101);
    }
}
