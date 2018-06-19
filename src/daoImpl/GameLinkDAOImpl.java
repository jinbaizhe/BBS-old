package daoImpl;

import dao.BaseDAO;
import dao.GameLinkDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vo.GameLink;
import java.util.List;

@Repository("gameLinkDAO")
public class GameLinkDAOImpl extends BaseDAO<GameLink> implements GameLinkDAO {
    @Override
    @Transactional(readOnly = true)
    public List getAllGameLinksByGameId(int id) {
        Session session=getSession();
        Query query=session.createQuery("from GameLink gl where gl.game.id=? and gl.orderNum>0 order by gl.orderNum asc");
        query.setParameter(0,id);
        List list=query.list();
//        session.close();
        return list;
    }
}
