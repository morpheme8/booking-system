package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Repository;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.dao.VerificationTokenDao;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.domain.VerificationToken;

import java.util.Date;
import java.util.List;

/**
 * Created by sylwek on 2016-06-14.
 */
@Repository
public class VerificationTokenDaoImpl extends AbstractDao <Integer, VerificationToken>
        implements VerificationTokenDao {


    public VerificationToken findByToken(String token) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("token", token));
        VerificationToken verificationToken = (VerificationToken) crit.uniqueResult();
        return verificationToken;
    }

    public boolean isTokenExpired(String token, Date now) {
        Criteria crit = createEntityCriteria();
        Criterion getToken = Restrictions.eq("token", token);
        Criterion isExpired = Restrictions.le("expiryDate", now);
        LogicalExpression expression = Restrictions.and(getToken, isExpired);
        crit.add(expression);
        if (crit.uniqueResult() == null) {
            return false;
        }
        return true;
    }

    public void save(VerificationToken verificationToken) {
        persist(verificationToken);
    }

    public void delete(String token) {
        delete(findByToken(token));
    }

}
