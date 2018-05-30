package service;

import vo.Post;

import java.io.File;
import java.util.List;

public interface PostService {
    public Post getPostById(int id);
    public void createPost(Post post);
    public void updatePost(Post post);
    public void deletePost(Post post);
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order);
    public int getPostsNumBySubForumId(int subForumId);
    public List getPostsByUserId(int userid);
    public void updatePostAllAttr(Post post);
    public List getSearchResults(String keyWord,int currentPage,int totalItemsPerPage,String order);
    public int getSearchResultNum(String keyWord,String order);
}
