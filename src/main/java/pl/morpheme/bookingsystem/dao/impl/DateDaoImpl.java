package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.DateDao;
import pl.morpheme.bookingsystem.domain.Date;
import pl.morpheme.bookingsystem.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-05-11.
 */
@Repository
public class DateDaoImpl extends AbstractDao<Integer, Date> implements DateDao {

    public Date findById(int id) {
        Date date = getByKey(id);
        if (date != null) {
            Hibernate.initialize(date.getRoomsForDate());
        }
        return date;
    }

    public List<Date> findExactDate(LocalDate date) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("date", date));
        List<Date> dates = (List<Date>) crit.list();
        if (dates!=null){
            for (Date date2 : dates) {
                Hibernate.initialize(date2.getRoomsForDate());
               List<Room> rooms =  date2.getRoomsForDate();
                for (Room room : rooms) {
                    Hibernate.initialize(room);
                }
            }
        }
        return dates;
    }

    public List<Date> findAllDates() {
        Criteria criteria = createEntityCriteria();
        List<Date> dates = (List<Date>) criteria.list();
        if (dates != null) {
            for (Date date : dates) {
                if (date.getRoomsForDate() != null){
                    Hibernate.initialize(date.getRoomsForDate());
                }
            }
        }
        return dates;
    }

    public void save(Date date) {
            if (date != null) {
                Hibernate.initialize(date.getBookings());
                Hibernate.initialize(date.getRoomsForDate());
            }
            persist(date);
    }

    public void delete(Date date) {
        if(date != null) {
            Hibernate.initialize(date.getRoomsForDate());
            Hibernate.initialize(date.getBookings());
        }
        delete(date);
    }
}
