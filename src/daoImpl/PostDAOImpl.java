package daoImpl;

import dao.BaseDAO;
import dao.PostDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import vo.Post;

import java.util.List;

public class PostDAOImpl extends BaseDAO<Post> implements PostDAO {
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPostById(int id)
    {
        Session session=getSession();
        String sql="from Post p where p.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,Integer.valueOf(id));
        List posts=query.list();
        Post post=null;
        session.close();
        if (posts.size()!=0)
            post=(Post) posts.get(0);
        return post;
    }

    @Override
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage) {
        Session session=getSession();
        String sql="from Post p where p.subForum.id=? order by sendTime desc";
        Query query=session.createQuery(sql);
        query.setParameter(0,subForumId);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List posts=query.list();
        session.close();
        return posts;
    }

    @Override
    public void createPost(Post post) {
        create(post);
    }

    @Override
    public void updatePost(Post post) {
        update(post);
    }

    @Override
    public void deletePost(Post post) {
        delete(post);
    }

    @Override
    public int getPostsNumBySubForumId(int subForumId) {
        Session session=getSession();
        String sql="from Post p where p.subForum.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,subForumId);
        int size=query.list().size();
        session.close();
        return size;
    }
}
