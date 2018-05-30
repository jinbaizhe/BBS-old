package daoImpl;

import dao.BaseDAO;
import dao.CollectionDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Collection;

import java.util.List;

public class CollectionDAOImpl extends BaseDAO<Collection> implements CollectionDAO {
    @Override
    public List getCollectionsByUserId(int userid,int currentPage,int totalItemsPerPage,String order) {
        Session session=getSession();
        String sql="from Collection c where c.id.user.id=? order by c.time "+order;
        Query query=session.createQuery(sql);
        query.setParameter(0,userid);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        return query.list();
    }

    @Override
    public List getCollectionsByPostId(int postid) {
        return null;
    }

    @Override
    public void createCollection(Collection collection) {
        create(collection);
    }

    @Override
    public void deleteCollection(int userid, int postid) {
        Session session=getSession();
        String sql="delete from Collection c where c.id.user.id=? and c.id.post.id=? ";
        Query query=session.createQuery(sql);
        query.setParameter(0,userid);
        query.setParameter(1,postid);
        query.executeUpdate();
    }

    @Override
    public Collection getCollection(int userid, int postid) {
        Session session=getSession();
        String sql="from Collection c where c.id.user.id=? and c.id.post.id=? ";
        Query query=session.createQuery(sql);
        query.setParameter(0,userid);
        query.setParameter(1,postid);
        List list=query.list();
        if(list.size()==0)
            return null;
        else
            return (Collection) list.get(0);
    }
}
