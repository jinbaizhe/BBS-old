package daoImpl;

import dao.BaseDAO;
import dao.FollowpostPictureDAO;
import vo.FollowpostPicture;

public class FollowpostPictureDAOImpl extends BaseDAO<FollowpostPicture> implements FollowpostPictureDAO {
    @Override
    public void createFollowpostPicture(FollowpostPicture followpostPicture) {
        create(followpostPicture);
    }

    @Override
    public void deleteFollowpostPicture(FollowpostPicture followpostPicture) {
        delete(followpostPicture);
    }
}
