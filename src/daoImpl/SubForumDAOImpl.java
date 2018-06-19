package daoImpl;

import dao.BaseDAO;
import dao.SubForumDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vo.SubForum;
import java.util.List;

@Repository("subForumDAO")
public class SubForumDAOImpl extends BaseDAO<SubForum> implements SubForumDAO {
    @Override
    @Transactional(readOnly = true)
    public List getSubForumsByMainForumId(int mainForumId) {
        Session session=getSession();
        String sql="from SubForum sf where sf.mainForum.id=? order by sf.posts.size desc";
        Query query=session.createQuery(sql);
        query.setParameter(0,mainForumId);
        List subForums=query.list();
//        session.close();
        return subForums;
    }

    @Override
    @Transactional(readOnly = true)
    public SubForum getSubForumById(int id) {
        Session session=getSession();
        String sql="from SubForum sf where sf.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,id);
        List subForums=query.list();
        SubForum subForum=null;
        if (subForums.size()!=0)
            subForum=(SubForum) subForums.get(0);
//        session.close();
        return subForum;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addSubForum(SubForum subForum) {
        create(subForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateSubForum(SubForum subForum) {
        update(subForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteSubForum(SubForum subForum) {
        delete(subForum);
    }
}
