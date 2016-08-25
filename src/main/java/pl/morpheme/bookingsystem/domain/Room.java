package pl.morpheme.bookingsystem.domain;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by sylwek on 2016-04-29.
 */

@Entity
@Table(name = "ROOM")
public class Room implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @NotNull
    @Column(name="BEDS", unique=false, nullable=false)
    private Integer beds;

    @NotNull
    @Column(name="TYPE", unique=false, nullable=false)
    private String type;

    @NotNull
    @Column(name="CAPACITY", unique=false, nullable=true)
    private Integer capacity;


   @ManyToMany(mappedBy="rooms")
    private Set<Booking> bookings;

    @ManyToMany(mappedBy="roomsForDate")
    private Set<Date> dates;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates = dates;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != null ? !id.equals(room.id) : room.id != null) return false;
        if (beds != null ? !beds.equals(room.beds) : room.beds != null) return false;
        if (type != null ? !type.equals(room.type) : room.type != null) return false;
        return capacity != null ? capacity.equals(room.capacity) : room.capacity == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (beds != null ? beds.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", type='" + type + '\'' +
                ", beds=" + beds +
                ", id=" + id +
                '}';
    }
}
