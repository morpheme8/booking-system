package pl.morpheme.bookingsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.service.RoomService;


/**
 * Created by sylwek on 2016-05-02.
 */
@Component
public class RoomTypeToRoom implements Converter<Object, Room> {

    @Autowired
    RoomService roomService;

    public Room convert(Object element) {
        if (element == null || element.getClass() == Room.class){
            return (Room)element;
        }
        Integer id = Integer.parseInt(element.toString());
        Room room = roomService.findById(id);
        return room;
    }

}