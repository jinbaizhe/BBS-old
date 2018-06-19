package daoImpl;

import dao.BaseDAO;
import dao.GameDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vo.Game;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("gameDAO")
public class GameDAOImpl extends BaseDAO<Game> implements GameDAO{
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
