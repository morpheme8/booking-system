package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.UserProfileDao;
import pl.morpheme.bookingsystem.domain.UserProfile;
import pl.morpheme.bookingsystem.service.UserProfileService;

import java.util.List;

/**
 * Created by sylwek on 2016-05-24.
 */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;

    public UserProfile findById(int id) {
        return dao.findById(id);
    }

    public UserProfile findByType(String type){
        return dao.findByType(type);
    }

    public List<UserProfile> findAll() {
        return dao.findAll();
    }
}
