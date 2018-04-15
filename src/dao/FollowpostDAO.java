package dao;

import vo.Followpost;

import java.util.List;

public interface FollowpostDAO {
    public Followpost getFollowpostById(int id);
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage);
    public int getFollowpostsNumByPostId(int postId);
    public void createFollowpost(Followpost followpost);
    public void updateFollowpost(Followpost followpost);
    public void deleteFollowpost(Followpost followpost);
}
