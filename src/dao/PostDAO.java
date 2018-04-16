package dao;

import vo.Post;

import java.util.List;

public interface PostDAO {
    public Post getPostById(int id);
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order);
    public int getPostsNumBySubForumId(int subForumId);
    public void createPost(Post post);
    public void updatePost(Post post);
    public void deletePost(Post post);
    public List getPostsByUserId(int userid,String order);
}
