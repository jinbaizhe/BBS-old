package daoImpl;

import dao.BaseDAO;
import dao.CollectionDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vo.Collection;
import java.util.List;

@Repository("collectionDAO")
public class CollectionDAOImpl extends BaseDAO<Collection> implements CollectionDAO {
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List getCollectionsByPostId(int postid) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createCollection(Collection collection) {
        create(collection);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteCollection(int userid, int postid) {
        Session session=getSession();
        String sql="delete from Collection c where c.id.user.id=? and c.id.post.id=? ";
        Query query=session.createQuery(sql);
        query.setParameter(0,userid);
        query.setParameter(1,postid);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
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
