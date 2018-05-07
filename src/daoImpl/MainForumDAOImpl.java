package daoImpl;

import dao.BaseDAO;
import dao.MainForumDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.Util;
import vo.MainForum;

import java.sql.Timestamp;
import java.util.List;

public class MainForumDAOImpl extends BaseDAO<MainForum> implements MainForumDAO {
    @Override
    public List getAllMainForums() {
        Session session=getSession();
        String sql="from MainForum mf order by createTime";
        Query query=session.createQuery(sql);
        List mainForums=query.list();
//        session.close();
        return mainForums;
    }

    @Override
    public MainForum getMainForumById(int id) {
        Session session=getSession();
        String sql="from MainForum mf where mf.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,id);
        List mainForums=query.list();
        MainForum mainForum=null;
        if(mainForums.size()!=0)
            mainForum=(MainForum) mainForums.get(0);
//        session.close();
        return mainForum;
    }

    @Override
    public void addMainForum(MainForum mainForum) {
        mainForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        create(mainForum);
    }

    @Override
    public void updateMainForum(MainForum mainForum) {
        update(mainForum);
    }

    @Override
    public void deleteMainForum(MainForum mainForum) {
        delete(mainForum);
    }
}
