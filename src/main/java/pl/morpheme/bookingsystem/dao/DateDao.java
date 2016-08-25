package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.Date;
import pl.morpheme.bookingsystem.domain.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-05-11.
 */
public interface DateDao {

    Date findById(int id);
    List<Date> findAllDates();
    void save(Date date);
    void delete(Date date);
    List <Date> findExactDate(LocalDate date);

}
