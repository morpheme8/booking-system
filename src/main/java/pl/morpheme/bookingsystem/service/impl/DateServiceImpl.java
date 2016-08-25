package pl.morpheme.bookingsystem.service.impl;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.DateDao;
import pl.morpheme.bookingsystem.domain.Date;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.service.DateService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-05-11.
 */
@Service
@Transactional
public class DateServiceImpl implements DateService {


    @Autowired
    private DateDao dateDao;

    static final Logger logger = LoggerFactory.getLogger(DateService.class);

    public Date findById(int id){
        return dateDao.findById(id);
    }

    public List<Date> findAllDates(){
        return dateDao.findAllDates();
    }

    public List <Date> findExactDate(LocalDate date) {
        return dateDao.findExactDate(date);
    }

    public void delete(Date date) {
        dateDao.delete(date);
    }

    public void save(Date date) {
        dateDao.save(date);
    }

    /**
     * Metoda checkWhatIsAvailable ma za zadanie sprawdzić, który z pokojów mających odpowiednią liczbę łóżek,
     * może zostać zaprezentowany użytkownikowi do wynajęcia. Mnogość pętli jest spowodowana koniecznością operacji
     * na wielu kolekcjach. Pętla while oblicza poszczególne dni, które mają być sprawdzone pod względem
     * dostępności. Pętla for sprawdza czy któraś z klendarzowych dat nie jest przypisana do istniejące rezerwacji
     * pokoju. W takim wypadku wskazany pokój zostaje usunięty ze zwracanej przez metodę kolekcji.
     * */
    public List <Room> checkWhatIsAvailable(LocalDate arD, LocalDate depD, List<Room> suitableRooms) {

        Set<Date> dates = new HashSet<Date>();
        while (!(arD.equals(depD))) {
            Date date = new Date();
            date.setDate(arD);
            date.setRoomsForDate(suitableRooms);
            dates.add(date);
            arD = arD.plusDays(1);
        }

        Set<Room> unavailableRooms = new HashSet<Room>();
            for (Date exactDate: dates){
                List<Date> dateFromDbToCheck = findExactDate(exactDate.getDate());
                if (!dateFromDbToCheck.isEmpty()) {
                    for (Date dateToCheck : dateFromDbToCheck) {
                        for (Room room : suitableRooms) {
                            if (dateToCheck.getRoomsForDate().contains(room)) {
                                unavailableRooms.add(room);
                            }
                        }
                    }
                }
            }
        for (Room room: unavailableRooms) {
            suitableRooms.remove(room);
        }
        return suitableRooms;
    }

}
