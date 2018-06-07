package daoImpl;

import dao.BaseDAO;
import dao.GameDAO;
import javafx.scene.input.DataFormat;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GameDAOImpl extends BaseDAO<Game> implements GameDAO{
    @Override
    public List getAllGames() {
        Session session=getSession();
        Query query=session.createQuery("from Game g where g.date= ?");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = simpleDateFormat.format(new Date());
        try {
            query.setParameter(0,simpleDateFormat.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List list=query.list();
//        session.close();
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
//        session.close();
        return game;
    }

    @Override
    public List getOldGames(int num) {
        Session session=getSession();
        Query query=session.createQuery("from Game g order by g.date desc");
        query.setFirstResult(0);
        query.setMaxResults(num);
        List list=query.list();
//        session.close();
        return list;
    }
}
