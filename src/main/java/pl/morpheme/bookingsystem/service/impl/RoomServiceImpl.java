package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.RoomDao;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.service.RoomService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    public List<Room> findAllRooms() {
        return roomDao.findAllRooms();
    }

    public Room findById(Integer id) {
        return roomDao.findById(id);
    }

    public List<Room> findRoomByNoOfBeds(int beds) {
        return roomDao.findRoomByNoOfBeds(beds);
    }

    public Room findOneByType(String type) {
        return roomDao.findOneByType(type);
    }

    public void save(Room room) { roomDao.save(room); }

    public void deleteById(int id) {
        roomDao.deleteById(id);
    }
}
