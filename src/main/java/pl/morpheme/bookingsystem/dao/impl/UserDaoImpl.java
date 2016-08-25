package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.UserDao;
import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-04-30.
 */
@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
            Hibernate.initialize(user.getBookings());
        }
        return user;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteById(Integer id) {
        User user = getByKey(id);
        delete(user);
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("lastName"));
        List<User> users = (List<User>) criteria.list();
        if(users != null) {
            Hibernate.initialize(users);
            }
            return users;
        }
    }
