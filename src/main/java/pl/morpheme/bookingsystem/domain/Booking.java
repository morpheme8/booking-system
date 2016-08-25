package pl.morpheme.bookingsystem.domain;

import pl.morpheme.bookingsystem.converter.LocalDateAttributeConverter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * Created by sylwek on 2016-04-29.
 */

@Entity
@Table(name = "BOOKING")
public class Booking  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @NotNull
    @Column(name="ARRIVAL_DATE", unique=false, nullable=false)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate arrivalDate;

    @NotNull
    @Column(name="DEPARTURE_DATE", unique=false, nullable=false)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate departureDate;

    @NotNull
    @Column(name="BEDS", unique=false, nullable=false)
    private Integer beds;

    @NotNull
    @Column(name="PEOPLE", unique=false, nullable=false)
    private Integer people;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name="BOOKING_ROOMS", joinColumns={@JoinColumn(referencedColumnName="ID")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="ID")})
    private List<Room> rooms;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="BOOKING_DATES", joinColumns={@JoinColumn(name="BOOKING_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="DATE_ID", referencedColumnName="ID")})
    private List<Date> bookedDates;

    public List<Date> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<Date> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (id != null ? !id.equals(booking.id) : booking.id != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(booking.arrivalDate) : booking.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(booking.departureDate) : booking.departureDate != null)
            return false;
        if (beds != null ? !beds.equals(booking.beds) : booking.beds != null) return false;
        if (people != null ? !people.equals(booking.people) : booking.people != null) return false;
        return user != null ? user.equals(booking.user) : booking.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (beds != null ? beds.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}

