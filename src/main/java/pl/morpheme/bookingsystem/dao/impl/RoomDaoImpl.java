package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.RoomDao;
import pl.morpheme.bookingsystem.domain.PersistentLogin;
import pl.morpheme.bookingsystem.domain.Room;

import java.util.List;

/**
 * Created by sylwek on 2016-05-02.
 */
@Repository
public class RoomDaoImpl extends AbstractDao<Integer, Room> implements RoomDao {

    public List<Room> findAllRooms() {
        Criteria criteria = createEntityCriteria();
        List<Room> rooms = (List<Room>) criteria.list();
        if(rooms != null) {
            for (Room room : rooms) {
                if(room.getBookings() != null){
                Hibernate.initialize(room.getBookings());}
                if(room.getDates() != null){
                Hibernate.initialize(room.getDates());

                }
            }
        }
        return rooms;
    }

    public Room findById(int id) {
        Room room = getByKey(id);
        if (room != null) {
            Hibernate.initialize(room.getBookings());
            Hibernate.initialize(room.getDates());
       }
        return room;
    }

    public List<Room> findRoomByNoOfBeds(int beds) {
        Criteria crit = createEntityCriteria();
        Criterion equalNumber = Restrictions.eq("beds", beds);
        Criterion orMore = Restrictions.gt("beds", beds);
        LogicalExpression orExprression = Restrictions.or(equalNumber, orMore);
        crit.add(orExprression);
        List<Room> rooms = crit.list();
        return rooms;
    }

    //DO WYJEBANIA
    public Room findOneByType(String type) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("type", type));
        criteria.setMaxResults(1);
        Room room = (Room) criteria.uniqueResult();
        return room;
    }

    public void save(Room room) {
        persist(room);
    }

    public void deleteById(int id) {
        Room room = getByKey(id);
        delete(room);
    }

}

