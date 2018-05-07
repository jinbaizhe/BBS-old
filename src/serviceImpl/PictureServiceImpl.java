package serviceImpl;

import dao.FollowpostPictureDAO;
import dao.PictureDAO;
import dao.PostPictureDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.PictureService;
import util.Util;
import vo.*;

import java.sql.Timestamp;
import java.util.List;

public class PictureServiceImpl implements PictureService {
    private PictureDAO pictureDAO;
    private PostPictureDAO postPictureDAO;
    private FollowpostPictureDAO followpostPictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    public PostPictureDAO getPostPictureDAO() {
        return postPictureDAO;
    }

    public void setPostPictureDAO(PostPictureDAO postPictureDAO) {
        this.postPictureDAO = postPictureDAO;
    }

    public FollowpostPictureDAO getFollowpostPictureDAO() {
        return followpostPictureDAO;
    }

    public void setFollowpostPictureDAO(FollowpostPictureDAO followpostPictureDAO) {
        this.followpostPictureDAO = followpostPictureDAO;
    }

    public void deletePicture(Picture picture)
    {
        pictureDAO.deletePicture(picture);
    }

    @Override
    public void deletePictureFromPost(Picture picture, Post post) {
        PostPicture postPicture=new PostPicture();
        postPicture.setPicture(picture);
        postPicture.setPost(post);
        Session session=postPictureDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            postPictureDAO.deletePostPicture(postPicture);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deletePictureFromFollowpost(Picture picture, Followpost followpost) {
        FollowpostPicture followpostPicture=new FollowpostPicture();
        followpostPicture.setPicture(picture);
        followpostPicture.setFollowpost(followpost);
        Session session=postPictureDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            followpostPictureDAO.deleteFollowpostPicture(followpostPicture);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Picture getPictureById(int id) {
        return pictureDAO.getPictureById(id);
    }

    @Override
    public List getPicturesByPostId(int id) {
        return pictureDAO.getPicturesByPostId(id);
    }

    @Override
    public List getPicturesByFollowpostId(int id) {
        return pictureDAO.getPicturesByFollowpostId(id);
    }
}
