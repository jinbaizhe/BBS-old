package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.Game;

import java.util.List;

public interface GameDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public List getAllGames();
    public Game getGameById(int id);
    public List getOldGames(int num);
}
