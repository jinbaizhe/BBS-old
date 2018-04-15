package daoImpl;

import dao.BaseDAO;
import dao.GameLinkDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.GameLink;
import java.util.List;

public class GameLinkDAOImpl extends BaseDAO<GameLink> implements GameLinkDAO {
    @Override
    public List getAllGameLinksByGameId(int id) {
        Session session=getSession();
        Query query=session.createQuery("from GameLink gl where gl.game.id=?");
        query.setParameter(0,id);
        List list=query.list();
        session.close();
        return list;
    }
}
