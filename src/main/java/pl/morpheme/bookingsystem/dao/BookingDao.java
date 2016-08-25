package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.User;

import java.util.List;

/**
 * Created by sylwek on 2016-05-01.
 */
public interface BookingDao {

    void save(Booking booking);
    void deleteById(Integer id);
    Booking findById(int id);
    List<Booking> findAllBookings();
    List<Booking> findAllBookingsForUser(User user);

}
