package serviceImpl;

import dao.FollowpostDAO;
import service.FollowpostService;
import util.Util;
import vo.Followpost;

import java.sql.Timestamp;
import java.util.List;

public class FollowpostServiceImpl implements FollowpostService {
    private FollowpostDAO followpostDAO;

    public FollowpostDAO getFollowpostDAO() {
        return followpostDAO;
    }

    public void setFollowpostDAO(FollowpostDAO followpostDAO) {
        this.followpostDAO = followpostDAO;
    }

    @Override
    public Followpost getFollowpostById(int id) {
        return followpostDAO.getFollowpostById(id);
    }

    @Override
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage) {
        return followpostDAO.getFollowpostsByPostId(postId,currentPage, totalItemsPerPage);
    }

    @Override
    public void createFollowpost(Followpost followpost) {
        followpost.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpostDAO.createFollowpost(followpost);
    }

    @Override
    public void updateFollowpost(Followpost followpost) {
        Followpost temp_followpost=followpostDAO.getFollowpostById(followpost.getId());
        temp_followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_followpost.setContent(followpost.getContent());
        followpostDAO.updateFollowpost(temp_followpost);
    }

    @Override
    public void deleteFollowpost(Followpost followpost) {
        followpostDAO.deleteFollowpost(followpost);
    }

    @Override
    public int getFollowpostsNumByPostId(int postId) {
        return followpostDAO.getFollowpostsNumByPostId(postId);
    }
}
