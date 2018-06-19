package serviceImpl;

import dao.PictureDAO;
import dao.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PostService;
import util.Util;
import vo.Post;
import java.sql.Timestamp;
import java.util.List;

@Component("postService")
public class PostServiceImpl implements PostService {
    private PostDAO postDAO;
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    @Autowired
    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    @Autowired
    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public Post getPostById(int id) {
        return postDAO.getPostById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createPost(Post post) {
        post.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        post.setTop(0);
        post.setType(0);
        post.setViewNum(0);
        post.setUpdateTime(post.getSendTime());
        postDAO.createPost(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updatePost(Post post) {
        Post temp_post=postDAO.getPostById(post.getId());
        temp_post.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_post.setTitle(post.getTitle());
        temp_post.setContent(post.getContent());
        postDAO.updatePost(temp_post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updatePostAllAttr(Post post) {
        postDAO.updatePost(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deletePost(Post post) {
        postDAO.deletePost(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order)
    {
        return postDAO.getPostsBySubForumId(subForumId, currentPage, totalItemsPerPage,order);
    }

    @Override
    @Transactional(readOnly = true)
    public int getPostsNumBySubForumId(int subForumId) {
        return postDAO.getPostsNumBySubForumId(subForumId);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPostsByUserId(int userid) {
        return postDAO.getPostsByUserId(userid,"sendTime desc");
    }

    @Override
    @Transactional(readOnly = true)
    public List getSearchResults(String keyWord,int currentPage,int totalItemsPerPage, String order) {
        return postDAO.getSearchResult(keyWord,currentPage,totalItemsPerPage,order);
    }

    @Override
    @Transactional(readOnly = true)
    public int getSearchResultNum(String keyWord, String order) {
        return postDAO.getSearchResultNum(keyWord,order);
    }
}
