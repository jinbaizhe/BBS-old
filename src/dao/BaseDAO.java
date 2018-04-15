package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public class BaseDAO<T> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession()
    {
        return getSessionFactory().openSession();
    }

    public T get(Class<T> entityClass, Serializable id)
    {
        return getSession().get(entityClass, id);
    }

    public void create(T entity)
    {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity)
    {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(T entity)
    {
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }
}
