package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.UserProfile;

import java.util.List;

/**
 * Created by sylwek on 2016-05-24.
 */

public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}