package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.BookingDao;
import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.Date;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.service.BookingService;
import pl.morpheme.bookingsystem.service.RoomService;
import pl.morpheme.bookingsystem.service.UserPrincipalService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
@Service
@Transactional
public class BookinkServiceImpl implements BookingService {

    @Autowired
    private BookingDao dao;
    @Autowired
    UserPrincipalService userPrincipalService;
    @Autowired
    RoomService roomService;

    public Booking findById(Integer id) {
        return dao.findById(id);
    }

    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    public List<Booking> findAllBookings() {
        return dao.findAllBookings();
    }

    public void saveBooking(Booking booking) {
        LocalDate arD = booking.getArrivalDate();
        LocalDate depD = booking.getDepartureDate();
        List<Room> bookedRooms = booking.getRooms();
        List<Date> dates = new LinkedList<Date>();
        while (!(arD.equals(depD))) {
            Date date = new Date();
            date.setDate(arD);
            date.setRoomsForDate(bookedRooms);
            dates.add(date);
            arD = arD.plusDays(1);
        }
        booking.setBookedDates(dates);

        int beds = booking.getRooms().size() * booking.getBeds();
        int people = booking.getRooms().size() * booking.getPeople();
        booking.setBeds(beds);
        booking.setPeople(people);
        dao.save(booking);
    }

    public List<Booking> findAllBookingsForUser(User user) {
        return dao.findAllBookingsForUser(user);
    }
}

