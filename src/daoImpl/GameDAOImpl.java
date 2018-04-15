package daoImpl;

import dao.BaseDAO;
import dao.GameDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GameDAOImpl extends BaseDAO<Game> implements GameDAO{
    @Override
    public List getAllGames() {
        Session session=getSession();
        Query query=session.createQuery("from Game g where g.date=?");
        query.setParameter(0,new Date());
        List list=query.list();
        session.close();
        return list;
    }

    @Override
    public Game getGameById(int id)
    {
        Session session=getSession();
        Query query=session.createQuery("from Game g where g.id=?");
        query.setParameter(0,id);
        List list=query.list();
        Game game=null;
        if (list.size()!=0)
            game=(Game)list.get(0);
        session.close();
        return game;
    }
}
