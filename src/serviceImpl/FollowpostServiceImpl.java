package serviceImpl;

import dao.FollowpostDAO;
import dao.FollowpostPictureDAO;
import dao.PictureDAO;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.FollowpostService;
import util.Util;
import vo.Followpost;
import vo.FollowpostPicture;
import vo.Picture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;

public class FollowpostServiceImpl implements FollowpostService {
    private FollowpostDAO followpostDAO;
    private FollowpostPictureDAO followpostPictureDAO;
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    public FollowpostPictureDAO getFollowpostPictureDAO() {
        return followpostPictureDAO;
    }

    public void setFollowpostPictureDAO(FollowpostPictureDAO followpostPictureDAO) {
        this.followpostPictureDAO = followpostPictureDAO;
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
    public void createFollowpost(Followpost followpost, List<File> files) {
        followpost.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));

        Session session = followpostDAO.getSession();
        session.beginTransaction();//事务开始
        try{
            followpostDAO.createFollowpost(followpost);
            String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");
            if(files!=null) {
                for (File source_file : files) {
                    File dest_file = new File(path + followpost.hashCode() + "" + source_file.hashCode() + ".jpg");
                    try {
                        Files.copy(source_file.toPath(), dest_file.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    source_file.delete();
                    Picture picture = new Picture();
                    picture.setPicture(dest_file.getName());
                    pictureDAO.createPicture(picture);
                    FollowpostPicture followpostPicture=new FollowpostPicture();
                    followpostPicture.setPicture(picture);
                    followpostPicture.setFollowpost(followpost);
                    followpostPictureDAO.createFollowpostPicture(followpostPicture);
                }
            }
            session.flush();
            session.getTransaction().commit();//事务提交
        }
        catch (Exception e)
        {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void updateFollowpost(Followpost followpost) {
        Followpost temp_followpost=followpostDAO.getFollowpostById(followpost.getId());
        temp_followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_followpost.setContent(followpost.getContent());
        Session session=followpostDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            followpostDAO.updateFollowpost(temp_followpost);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deleteFollowpost(Followpost followpost) {
        Session session=followpostDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            followpostDAO.deleteFollowpost(followpost);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public int getFollowpostsNumByPostId(int postId) {
        return followpostDAO.getFollowpostsNumByPostId(postId);
    }

    @Override
    public List getFollowpostsByUserId(int userid) {
        return followpostDAO.getFollowpostsByUserId(userid,"sendTime desc");
    }
}
