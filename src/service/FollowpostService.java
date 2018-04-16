package service;

import vo.Followpost;

import java.util.List;

public interface FollowpostService {
    public Followpost getFollowpostById(int id);
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage,String order);
    public void createFollowpost(Followpost followpost);
    public void updateFollowpost(Followpost followpost);
    public void deleteFollowpost(Followpost followpost);
    public int getFollowpostsNumByPostId(int postId);
    public List getFollowpostsByUserId(int userid);
}
