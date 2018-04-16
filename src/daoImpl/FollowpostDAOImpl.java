package daoImpl;

import dao.BaseDAO;
import dao.FollowpostDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Followpost;

import java.util.List;

public class FollowpostDAOImpl extends BaseDAO<Followpost> implements FollowpostDAO{
    @Override
    public Followpost getFollowpostById(int id) {
        Session session=getSession();
        String sql="from Followpost f where f.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,id);
        List followposts=query.list();
        Followpost followpost=null;
        session.close();
        if (followposts.size()!=0)
            followpost=(Followpost) followposts.get(0);
        return followpost;
    }

    @Override
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage,String order) {
        Session session=getSession();
        String sql="from Followpost f where f.post.id=? order by "+order;
        Query query=session.createQuery(sql);
        query.setParameter(0,postId);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List followposts=query.list();
        session.close();
        return followposts;
    }

    @Override
    public void createFollowpost(Followpost followpost) {
        create(followpost);
    }

    @Override
    public void updateFollowpost(Followpost followpost) {
        update(followpost);
    }

    @Override
    public void deleteFollowpost(Followpost followpost) {
        delete(followpost);
    }

    @Override
    public int getFollowpostsNumByPostId(int postId) {
        Session session=getSession();
        String sql="from Followpost f where f.post.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,postId);
        int size=query.list().size();
        session.close();
        return size;
    }

    @Override
    public List getFollowpostsByUserId(int userid,String order) {
        Session session=getSession();
        Query query=session.createQuery("from Followpost f where f.user.id=? order by "+order);
        query.setParameter(0,userid);
        List followposts=query.list();
        session.close();
        return followposts;
    }
}
