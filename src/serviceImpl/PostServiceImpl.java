package serviceImpl;

import dao.PostDAO;
import service.PostService;
import util.Num;
import util.Util;
import vo.Post;

import java.sql.Timestamp;
import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDAO postDAO;

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
        postDAO.createPost(post);
    }

    @Override
    public void updatePost(Post post) {
        Post temp_post=postDAO.getPostById(post.getId());
        temp_post.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        temp_post.setTitle(post.getTitle());
        temp_post.setContent(post.getContent());
        postDAO.updatePost(temp_post);
    }

    @Override
    public void deletePost(Post post) {
        postDAO.deletePost(post);
    }

    @Override
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage) {
        return postDAO.getPostsBySubForumId(subForumId, currentPage, totalItemsPerPage);
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        return postDAO.getPostsNumBySubForumId(subForumId);
    }
}
