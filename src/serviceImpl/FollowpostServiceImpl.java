package serviceImpl;

import dao.FollowpostDAO;
import dao.PictureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.FollowpostService;
import util.Util;
import vo.Followpost;
import java.sql.Timestamp;
import java.util.List;

@Component("followpostService")
public class FollowpostServiceImpl implements FollowpostService {
    @Autowired
    private FollowpostDAO followpostDAO;
    @Autowired
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }


    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

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
    public List getFollowpostsByPostId(int postId,int currentPage,int totalItemsPerPage,String order) {
        order="sendTime "+order;
        return followpostDAO.getFollowpostsByPostId(postId,currentPage, totalItemsPerPage,order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createFollowpost(Followpost followpost) {
        followpost.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpostDAO.createFollowpost(followpost);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateFollowpost(Followpost followpost) {
        Followpost temp_followpost=followpostDAO.getFollowpostById(followpost.getId());
        temp_followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_followpost.setContent(followpost.getContent());
        followpostDAO.updateFollowpost(temp_followpost);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteFollowpost(Followpost followpost) {
        followpostDAO.deleteFollowpost(followpost);
    }

    @Override
    @Transactional(readOnly = true)
    public int getFollowpostsNumByPostId(int postId) {
        return followpostDAO.getFollowpostsNumByPostId(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List getFollowpostsByUserId(int userid) {
        return followpostDAO.getFollowpostsByUserId(userid,"sendTime desc");
    }
}
