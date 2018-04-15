package daoImpl;

import dao.BaseDAO;
import dao.PictureDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Picture;

import java.util.List;

public class PictureDAOImpl extends BaseDAO<Picture> implements PictureDAO {
    @Override
    public void createPicture(Picture picture) {
        create(picture);
    }

    @Override
    public void deletePicture(Picture picture) {
        delete(picture);
    }

    @Override
    public Picture getPictureById(int id) {
        Session session=getSession();
        Query query=session.createQuery("from Picture p where p.id=?");
        query.setParameter(0,id);
        List<Picture> pictures=query.list();
        session.close();
        Picture picture=null;
        if(pictures.size()!=0)
            picture=pictures.get(0);
        return picture;
    }

    @Override
    public List getPicturesByPostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("from PostPicture pp where pp.post.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
        session.close();
        return pictures;
    }

    @Override
    public List getPicturesByFollowpostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("from FollowpostPicture fp where fp.followpost.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
        session.close();
        return pictures;
    }
}
