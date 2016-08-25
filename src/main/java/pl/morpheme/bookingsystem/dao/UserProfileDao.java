package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.UserProfile;

import java.util.List;

/**
 * Created by sylwek on 2016-05-24.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();
    UserProfile findByType(String type);
    UserProfile findById(int id);
}