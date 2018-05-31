package daoImpl;

import dao.BaseDAO;
import dao.SubForumDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.SubForum;

import java.util.List;

public class SubForumDAOImpl extends BaseDAO<SubForum> implements SubForumDAO {
    @Override
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
    public void addSubForum(SubForum subForum) {
        create(subForum);
    }

    @Override
    public void updateSubForum(SubForum subForum) {
        update(subForum);
    }

    @Override
    public void deleteSubForum(SubForum subForum) {
        delete(subForum);
    }
}
