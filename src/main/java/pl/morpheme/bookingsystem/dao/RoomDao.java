package pl.morpheme.bookingsystem.dao;


import pl.morpheme.bookingsystem.domain.Room;

import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
public interface RoomDao {

    Room findById(int id);
    List<Room> findAllRooms();
    List<Room> findRoomByNoOfBeds(int beds);
    Room findOneByType(String type);
    void save(Room room);
    void deleteById(int id);

}
