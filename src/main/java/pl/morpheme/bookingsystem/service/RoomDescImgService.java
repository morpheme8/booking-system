package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.RoomDescImg;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by sylwek on 2016-06-20.
 */
public interface RoomDescImgService {

    RoomDescImg findById(int id);
    RoomDescImg findByImgUrl(String url);
    List<RoomDescImg> findByRoomType(String roomType);
    List<RoomDescImg> findAll();
    void saveAndUploadImage(RoomDescImg roomDescImg, HttpServletRequest request) throws IOException;
    void deleteById(int id);

}
