package daoImpl;

import dao.BaseDAO;
import dao.PostPictureDAO;
import vo.PostPicture;

public class PostPictureDAOImpl extends BaseDAO<PostPicture> implements PostPictureDAO {
    @Override
    public void createPostPicture(PostPicture postPicture) {
        create(postPicture);
    }

    @Override
    public void deletePostPicture(PostPicture postPicture) {
        delete(postPicture);
    }
}
