package daoImpl;

import dao.BaseDAO;
import dao.PictureDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.Util;
import vo.Picture;

import java.sql.Timestamp;
import java.util.List;

public class PictureDAOImpl extends BaseDAO<Picture> implements PictureDAO {
    @Override
    public void createPicture(Picture picture) {
        picture.setUploadTime(Timestamp.valueOf(Util.getCurrentDateTime()));
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
//        session.close();
        Picture picture=null;
        if(pictures.size()!=0)
            picture=pictures.get(0);
        return picture;
    }

    @Override
    public List getPicturesByPostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("select pp.picture from PostPicture pp where pp.post.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
//        session.close();
        return pictures;
    }

    @Override
    public List getPicturesByFollowpostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("select fp.picture from FollowpostPicture fp where fp.followpost.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
//        session.close();
        return pictures;
    }
}
