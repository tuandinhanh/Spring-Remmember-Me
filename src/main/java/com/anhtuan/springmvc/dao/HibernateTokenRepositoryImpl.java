package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.PersistentLogins;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

@Repository("customPersistentTokenRepository")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogins>
        implements PersistentTokenRepository {
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogins persistentLogins = new PersistentLogins();
        persistentLogins.setSeries(persistentRememberMeToken.getSeries());
        persistentLogins.setUsername(persistentRememberMeToken.getUsername());
        persistentLogins.setToken(persistentRememberMeToken.getTokenValue());
        persistentLogins.setLast_used(persistentRememberMeToken.getDate());
        persist(persistentLogins);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date last_used) {
        PersistentLogins persistentLogins = getByKey(series);
        persistentLogins.setToken(tokenValue);
        persistentLogins.setLast_used(last_used);
        update(persistentLogins);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<PersistentLogins> criteriaQuery = criteriaBuilder.createQuery(PersistentLogins.class);
        Root<PersistentLogins> persistentLoginsRoot = criteriaQuery.from(PersistentLogins.class);
        criteriaQuery.select(persistentLoginsRoot).where(criteriaBuilder.equal(persistentLoginsRoot.get("series"), series));
        try {
            PersistentLogins persistentLogins = (PersistentLogins) getSession().createQuery(criteriaQuery).getSingleResult();
            return new PersistentRememberMeToken(persistentLogins.getUsername(), persistentLogins.getSeries(), persistentLogins.getToken(), persistentLogins.getLast_used());
        } catch (NoResultException e) {
            // ----------Log-----------------
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        try {
            CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
            CriteriaQuery<PersistentLogins> criteriaQuery = criteriaBuilder.createQuery(PersistentLogins.class);
            Root<PersistentLogins> persistentLoginsRoot = criteriaQuery.from(PersistentLogins.class);
            criteriaQuery.select(persistentLoginsRoot).where(criteriaBuilder.equal(persistentLoginsRoot.get("username"), username));
            PersistentLogins persistentLogins = (PersistentLogins) getSession().createQuery(criteriaQuery).getSingleResult();
            if (persistentLogins != null) delete(persistentLogins);
        } catch (NoResultException e) {
            //-----------------Log--------------
        }
    }
}
