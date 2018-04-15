package service;

import vo.Post;

import java.util.List;

public interface PostService {
    public Post getPostById(int id);
    public void createPost(Post post);
    public void updatePost(Post post);
    public void deletePost(Post post);
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage);
    public int getPostsNumBySubForumId(int subForumId);
}
