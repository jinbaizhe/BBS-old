package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.PostPicture;

public interface PostPictureDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public void createPostPicture(PostPicture postPicture);
    public void deletePostPicture(PostPicture postPicture);
}
