package pl.morpheme.bookingsystem.service;

import pl.morpheme.bookingsystem.domain.VerificationToken;

import java.util.Date;
import java.util.List;

/**
 * Created by sylwek on 2016-06-14.
 */
public interface VerificationTokenService {

    void save(VerificationToken verificationToken);

    void delete(String token);

    VerificationToken findByToken(String token);

    boolean isTokenExpired(String token);

}

