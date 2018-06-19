package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
public class BaseDAO<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession()
    {
        return getSessionFactory().getCurrentSession();
    }

    public T get(Class<T> entityClass, Serializable id)
    {
        return getSession().get(entityClass, id);
    }

    public void create(T entity)
    {
        Session session=getSession();
        session.save(entity);
//        session.close();
    }

    public void update(T entity)
    {
        Session session=getSession();
        session.update(entity);
//        session.close();
    }

    public void delete(T entity)
    {
        Session session=getSession();
        session.delete(entity);
//        session.close();
    }
}
