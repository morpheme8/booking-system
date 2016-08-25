package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.User;

import java.util.List;

/**
 * Created by sylwek on 2016-04-30.
 */
public interface UserDao {

    User findById(int id);
    User findByEmail(String email);
    void save(User user);
    void deleteById(Integer id);
    List<User> findAllUsers();

}
