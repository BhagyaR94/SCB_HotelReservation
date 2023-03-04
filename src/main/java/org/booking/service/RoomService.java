package org.booking.service;

import java.util.List;

public interface RoomService {

    void addNewRoom(int nroomNumber);

    List<Integer> findAvailableRooms();

    void removeFromAvailable(int roomId);

    boolean isValidRoomNumber(int roomNumber);

    boolean isRoomAvailable(int roomNumber);

    boolean isRoomBookable(int roomNumber);
}
