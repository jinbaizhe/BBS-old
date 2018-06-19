package daoImpl;

import dao.BaseDAO;
import dao.PictureDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.Util;
import vo.Picture;
import java.sql.Timestamp;
import java.util.List;

@Repository("pictureDAO")
public class PictureDAOImpl extends BaseDAO<Picture> implements PictureDAO {
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createPicture(Picture picture) {
        picture.setUploadTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        create(picture);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deletePicture(Picture picture) {
        delete(picture);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List getPicturesByPostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("select pp.id.picture from PostPicture pp where pp.id.picture.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
//        session.close();
        return pictures;
    }

    @Override
    @Transactional(readOnly = true)
    public List getPicturesByFollowpostId(int id) {
        Session session=getSession();
        Query query=session.createQuery("select fp.id.picture from FollowpostPicture fp where fp.id.followpost.id=?");
        query.setParameter(0,id);
        List pictures=query.list();
//        session.close();
        return pictures;
    }
}
