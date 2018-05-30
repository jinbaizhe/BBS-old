package serviceImpl;

import dao.PictureDAO;
import dao.PostDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.PostService;
import util.Util;
import vo.Post;
import java.sql.Timestamp;
import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDAO postDAO;
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    public Post getPostById(int id) {
        return postDAO.getPostById(id);
    }

    @Override
    public void createPost(Post post) {
        post.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        post.setTop(0);
        post.setType(0);
        post.setViewNum(0);
        post.setUpdateTime(post.getSendTime());

        Session session = postDAO.getSession();
        session.beginTransaction();//事务开始
        try {
            postDAO.createPost(post);
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
    public void updatePost(Post post) {
        Post temp_post=postDAO.getPostById(post.getId());
        temp_post.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_post.setTitle(post.getTitle());
        temp_post.setContent(post.getContent());
        Session session=postDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            postDAO.updatePost(temp_post);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void updatePostAllAttr(Post post) {
        Session session=postDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            postDAO.updatePost(post);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deletePost(Post post) {
        Session session=postDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            postDAO.deletePost(post);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order)
    {
        return postDAO.getPostsBySubForumId(subForumId, currentPage, totalItemsPerPage,order);
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        return postDAO.getPostsNumBySubForumId(subForumId);
    }

    @Override
    public List getPostsByUserId(int userid) {
        return postDAO.getPostsByUserId(userid,"sendTime desc");
    }

    @Override
    public List getSearchResults(String keyWord,int currentPage,int totalItemsPerPage, String order) {
        return postDAO.getSearchResult(keyWord,currentPage,totalItemsPerPage,order);
    }

    @Override
    public int getSearchResultNum(String keyWord, String order) {
        return postDAO.getSearchResultNum(keyWord,order);
    }
}
