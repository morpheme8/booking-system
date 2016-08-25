package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.RoomDescImg;
import pl.morpheme.bookingsystem.domain.RoomType;

import java.util.List;

/**
 * Created by sylwek on 2016-06-20.
 */
public interface RoomDescImgDao {

    RoomDescImg findById(int id);
    RoomDescImg findByImgUrl(String url);
    List<RoomDescImg> findByRoomType(String roomType);
    List<RoomDescImg> findAll();
    void save(RoomDescImg roomDescImg);
    void deleteById(int id);

}
