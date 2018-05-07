package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public interface GameLinkDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public List getAllGameLinksByGameId(int id);
}
