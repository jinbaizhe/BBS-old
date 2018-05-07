package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.Followpost;

import java.util.List;

public interface FollowpostDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public Followpost getFollowpostById(int id);
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage,String order);
    public int getFollowpostsNumByPostId(int postId);
    public void createFollowpost(Followpost followpost);
    public void updateFollowpost(Followpost followpost);
    public void deleteFollowpost(Followpost followpost);
    public List getFollowpostsByUserId(int userid,String order);
}
