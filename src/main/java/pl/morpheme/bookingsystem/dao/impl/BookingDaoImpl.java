package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.BookingDao;
import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.domain.User;


import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-05-01.
 */
@Repository
public class BookingDaoImpl extends AbstractDao<Integer,Booking> implements BookingDao {

    public void deleteById(Integer id) {
        Booking booking = getByKey(id);
        delete(booking);
    }

    public void save(Booking booking) {
      if(booking != null) {
          Hibernate.initialize(booking.getRooms());
      }
    persist(booking);
    }

    public List<Booking> findAllBookings() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("id"));
        List<Booking> bookings = (List<Booking>) criteria.list();
        initialize(bookings);
        return bookings;
    }

    public List<Booking> findAllBookingsForUser(User user) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user", user));
        List<Booking> bookings = (List<Booking>) criteria.list();
        initialize(bookings);
        return bookings;
    }

    public Booking findById(int id) {
        Booking booking = getByKey(id);
      if(booking != null){
            Hibernate.initialize(booking.getId());
        }
        return booking;
    }

    private void initialize(List<Booking> bookings) {
        if (bookings != null) {
            for (Booking booking: bookings) {
                List<Room> rooms = booking.getRooms();
                for (Room room : rooms) {
                    Hibernate.initialize(room);
                }
            }
        }
    }

}
