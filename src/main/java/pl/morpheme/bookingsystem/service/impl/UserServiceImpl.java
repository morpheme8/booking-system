package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.UserDao;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.service.UserService;

import java.util.List;

/**
 * Created by sylwek on 2016-04-30.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Integer id) {
        return dao.findById(id);
    }

    public void saveUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    public void updateUser(User user) {
        dao.save(user);
    }

    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public void deleteUserById(Integer id) {
        dao.deleteById(id);
    }

    public List<User> findAllUser() {
        return dao.findAllUsers();
    }
}
