package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.FollowpostPicture;

public interface FollowpostPictureDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public void createFollowpostPicture(FollowpostPicture followpostPicture);
    public void deleteFollowpostPicture(FollowpostPicture followpostPicture);
}
