package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.VerificationTokenDao;
import pl.morpheme.bookingsystem.domain.VerificationToken;
import pl.morpheme.bookingsystem.service.VerificationTokenService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sylwek on 2016-06-14.
 */

@Service
@Transactional
public class VerificationTokenSerivceImpl implements VerificationTokenService{

    @Autowired
    private VerificationTokenDao dao;

    private static final int EXPIRATION = 2; //60 * 24

    public void save(VerificationToken verificationToken) {
        verificationToken.setExpiryDate(calculateExpiryDate(EXPIRATION));
        dao.save(verificationToken);
    }

    public void delete(String token) {
        dao.delete(token);
    }

    public VerificationToken findByToken(String token) {
        return dao.findByToken(token);
    }

    public boolean isTokenExpired(String token) {
        return dao.isTokenExpired(token, getNow());
    }

    private Date calculateExpiryDate(int exp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, exp);
        return new Date(cal.getTime().getTime());
    }

    private Date getNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        return cal.getTime();
    }

}
