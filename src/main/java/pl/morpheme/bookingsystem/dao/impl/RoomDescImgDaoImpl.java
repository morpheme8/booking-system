package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.RoomDescImgDao;
import pl.morpheme.bookingsystem.domain.RoomDescImg;

import java.util.List;

/**
 * Created by sylwek on 2016-06-20.
 */
@Repository
public class RoomDescImgDaoImpl extends AbstractDao<Integer, RoomDescImg> implements RoomDescImgDao {

    public List<RoomDescImg> findByRoomType(String roomType) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ROOM_TYPE", roomType));
        List<RoomDescImg> roomDescImgs = (List<RoomDescImg>) crit.list();
        return roomDescImgs;
    }

    public List<RoomDescImg> findAll() {
        Criteria crit = createEntityCriteria();
        List<RoomDescImg> roomDescImgs = (List<RoomDescImg>) crit.list();
        return roomDescImgs;
    }

    public RoomDescImg findById(int id) {
        RoomDescImg roomDescImg = getByKey(id);
        return roomDescImg;
    }

    public RoomDescImg findByImgUrl(String url) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("IMAGE_URL", url));
        RoomDescImg roomDescImg = (RoomDescImg) crit.uniqueResult();
        return roomDescImg;
    }

    public void save(RoomDescImg roomDescImg) {
        persist(roomDescImg);
    }

    public void deleteById(int id) {
        RoomDescImg roomDescImg = getByKey(id);
        delete(roomDescImg);
    }
}
