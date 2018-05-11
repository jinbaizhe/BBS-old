package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vo.Post;

import java.util.List;

public interface PostDAO {
    public Session getSession();
    public SessionFactory getSessionFactory();
    public Post getPostById(int id);
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order);
    public int getPostsNumBySubForumId(int subForumId);
    public void createPost(Post post);
    public void updatePost(Post post);
    public void deletePost(Post post);
    public List getPostsByUserId(int userid,String order);
    public List getSearchResult(String keyWord,int currentPage,int totalItemsPerPage, String order);
    public int getSearchResultNum(String keyWord,String order);
}
