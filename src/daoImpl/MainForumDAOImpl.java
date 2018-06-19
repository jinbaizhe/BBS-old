package daoImpl;

import dao.BaseDAO;
import dao.MainForumDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.Util;
import vo.MainForum;
import java.sql.Timestamp;
import java.util.List;

@Repository("mainForumDAO")
public class MainForumDAOImpl extends BaseDAO<MainForum> implements MainForumDAO {
    @Override
    @Transactional(readOnly = true)
    public List getAllMainForums() {
        Session session=getSession();
        String sql="from MainForum mf order by mf.subForums.size desc";
        Query query=session.createQuery(sql);
        List mainForums=query.list();
//        session.close();
        return mainForums;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addMainForum(MainForum mainForum) {
        mainForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        create(mainForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateMainForum(MainForum mainForum) {
        update(mainForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteMainForum(MainForum mainForum) {
        delete(mainForum);
    }
}
