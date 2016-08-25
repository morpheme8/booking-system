package pl.morpheme.bookingsystem.domain;

import pl.morpheme.bookingsystem.converter.LocalDateAttributeConverter;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-05-07.
 */
@Entity
@Table(name = "DATE")
public class Date implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name="DATE", unique=false, nullable=false)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate date;

    @ManyToMany
    @JoinTable(name="ROOM_DATE", joinColumns={@JoinColumn(name="DATE_ID", referencedColumnName="ID")}
            ,inverseJoinColumns={@JoinColumn(name="ROOM_ID", referencedColumnName="ID")})
    private List<Room> roomsForDate;

    @ManyToMany(mappedBy="bookedDates")
    private List<Booking> bookings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Room> getRoomsForDate() {
        return roomsForDate;
    }

    public void setRoomsForDate(List<Room> roomsForDate) {
        this.roomsForDate = roomsForDate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date1 = (Date) o;

        if (id != null ? !id.equals(date1.id) : date1.id != null) return false;
        return date != null ? date.equals(date1.date) : date1.date == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", date=" + date +
                ", roomsForDate=" + roomsForDate +
                '}';
    }
}
