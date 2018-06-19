package daoImpl;

import dao.BaseDAO;
import dao.FollowpostDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vo.Followpost;
import java.util.List;

@Repository("followpostDAO")
public class FollowpostDAOImpl extends BaseDAO<Followpost> implements FollowpostDAO{
    @Override
    @Transactional(readOnly = true)
    public Followpost getFollowpostById(int id) {
        Session session=getSession();
        String sql="from Followpost f where f.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,id);
        List followposts=query.list();
        Followpost followpost=null;
//        session.close();
        if (followposts.size()!=0)
            followpost=(Followpost) followposts.get(0);
        return followpost;
    }

    @Override
    @Transactional(readOnly = true)
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage,String order) {
        Session session=getSession();
        String sql="from Followpost f where f.post.id=? order by "+order;
        Query query=session.createQuery(sql);
        query.setParameter(0,postId);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List followposts=query.list();
//        session.close();
        return followposts;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createFollowpost(Followpost followpost) {
        create(followpost);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateFollowpost(Followpost followpost) {
        update(followpost);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteFollowpost(Followpost followpost) {
        delete(followpost);
    }

    @Override
    @Transactional(readOnly = true)
    public int getFollowpostsNumByPostId(int postId) {
        Session session=getSession();
        String sql="select count(f.id) from Followpost f where f.post.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,postId);
//        session.close();
        return ((Long)query.list().get(0)).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List getFollowpostsByUserId(int userid,String order) {
        Session session=getSession();
        Query query=session.createQuery("from Followpost f where f.user.id=? order by "+order);
        query.setParameter(0,userid);
        List followposts=query.list();
//        session.close();
        return followposts;
    }
}
