package pl.morpheme.bookingsystem.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.dao.AbstractDao;
import pl.morpheme.bookingsystem.domain.PersistentLogin;

import java.util.Date;
import java.util.List;

/**
 * Created by sylwek on 2016-05-24.
 */

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

    static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

    public void createNewToken(PersistentRememberMeToken token) {

        logger.info("TWORZENIE TOKENU DLA USERA : {}", token.getUsername());

       PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);
    }

    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Odświerz token dla serii : {}", seriesId);
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }

    public PersistentRememberMeToken getTokenForSeries(String series) {
        logger.info("Podaj token jeśli istniej dla series : {}", series);
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", series));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeUserTokens(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        List<PersistentLogin> persistentLogin = crit.list();
        if (persistentLogin != null) {
            for(PersistentLogin pl:persistentLogin) {
                delete(pl);
            }
        }

    }
}
