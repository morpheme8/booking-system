package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.User;

import java.util.List;

/**
 * Created by sylwek on 2016-04-30.
 */
public interface UserService {

    User findById(Integer id);
    User findByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(Integer id);
    List<User> findAllUser();




}
