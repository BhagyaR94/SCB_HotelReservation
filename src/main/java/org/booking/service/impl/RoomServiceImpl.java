package org.booking.service.impl;

import org.booking.exception.RoomReservationException;
import org.booking.service.RoomService;

import java.util.*;

public class RoomServiceImpl implements RoomService {

    private final Set<Integer> availableRooms;
    private final Set<Integer> bookedRooms;

    public RoomServiceImpl() {
        this.availableRooms = new HashSet<>();
        this.bookedRooms = new HashSet<>();
        insertAllRooms();
    }

    @Override
    public void addNewRoom(int roomNumber) {
        if (availableRooms.contains(roomNumber)) {
            throw new RoomReservationException("Room Already Available");
        } else {
            availableRooms.add(roomNumber);
        }
    }

    @Override
    public List<Integer> findAvailableRooms() {
        return new ArrayList<>(availableRooms);
    }

    @Override
    public void removeFromAvailable(int roomId) {
        availableRooms.remove(roomId);
        bookedRooms.add(roomId);
    }

    @Override
    public boolean isValidRoomNumber(int roomNumber) {
        return availableRooms.contains(roomNumber) || bookedRooms.contains(roomNumber);
    }

    @Override
    public boolean isRoomAvailable(int roomNumber) {
        return availableRooms.contains(roomNumber);
    }

    @Override
    public boolean isRoomBookable(int roomNumber) {
        if (!isValidRoomNumber(roomNumber)) {
            throw new RoomReservationException("Invalid Room Number");
        } else if(!isRoomAvailable(roomNumber)){
            throw new RoomReservationException("Room Not Available");
        }
        return isValidRoomNumber(roomNumber) && isRoomAvailable(roomNumber);
    }

    private void insertAllRooms() {
        availableRooms.add(101);
        availableRooms.add(102);
        availableRooms.add(103);
        availableRooms.add(104);
        availableRooms.add(105);
        availableRooms.add(201);
        availableRooms.add(202);
        availableRooms.add(203);
        availableRooms.add(204);
        availableRooms.add(205);
        availableRooms.add(301);
        availableRooms.add(302);
        availableRooms.add(303);
        availableRooms.add(304);
        availableRooms.add(305);
    }
}
