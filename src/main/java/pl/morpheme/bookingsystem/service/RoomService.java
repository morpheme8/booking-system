package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.Room;

import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
public interface RoomService {

    List<Room> findAllRooms();
    Room findById(Integer id);
    List<Room> findRoomByNoOfBeds(int beds);
    Room findOneByType(String type);
    void save(Room room);
    void deleteById(int id);

}
