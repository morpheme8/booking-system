package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.User;


import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
public interface BookingService {


    Booking findById(Integer id);
    void saveBooking(Booking booking);
    void deleteById(Integer id);
    List<Booking> findAllBookings();
    List<Booking> findAllBookingsForUser(User user);
}
