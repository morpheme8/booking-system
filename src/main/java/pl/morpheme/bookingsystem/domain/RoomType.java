package pl.morpheme.bookingsystem.domain;

import java.io.Serializable;

/**
 * Created by sylwek on 2016-06-20.
 */
public enum RoomType implements Serializable {

    TWIN("twin"),
    STUDIO("studio"),
    COMFORT("comfort"),
    FAMILY_ROOM("family_room"),
    SUITE("suite");

    private String roomType;

    private RoomType(String roomType){
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return this.roomType;
    }
}
