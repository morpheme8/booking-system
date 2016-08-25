package pl.morpheme.bookingsystem.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by sylwek on 2016-04-30.
 */
public abstract class AbstractDao <PK extends Serializable, T>{

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass

                ()).getActualTypeArguments()[1];
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);

    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();

    }

    public T getByKey(PK key){
        return (T) getSession().get(persistentClass, key);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void persist(T entity){
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity){
        getSession().delete(entity);
    }


}
