package pl.morpheme.bookingsystem.dao;

import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.domain.VerificationToken;

import java.util.Date;
import java.util.List;

/**
 * Created by sylwek on 2016-06-14.
 */
public interface VerificationTokenDao {

    VerificationToken findByToken(String token);
    boolean isTokenExpired(String token, Date now);
    void delete(String token);
    void save(VerificationToken verificationToken);


}
