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
//        session.close();
        if (posts.size()!=0)
            post=(Post) posts.get(0);
        return post;
    }

    @Override
    public List getPostsBySubForumId(int subForumId,int currentPage,int totalItemsPerPage,String order) {
        Session session=getSession();
        String sql="select p, coalesce(max(f.sendTime),p.sendTime) as lastfollowpost " +
                " from Post p left join Followpost f on p.id=f.post.id where p.subForum.id=? " +
                "group by p.id order by p.top desc,";
        if(order.equals("lastfollowpost"))
            sql=sql+"lastfollowpost desc";
        else if (order.equals("postsend"))
            sql=sql+"p.sendTime desc";
        Query query=session.createQuery(sql);
        query.setParameter(0,subForumId);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List posts=query.list();
//        session.close();
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
//        session.close();
        return size;
    }

    @Override
    public List getPostsByUserId(int userid,String order) {
        Session session=getSession();
        Query query=session.createQuery("from Post p where p.user.id=? order by "+order);
        query.setParameter(0,userid);
        List posts=query.list();
//        session.close();
        return posts;
    }

    @Override
    public List getSearchResult(String keyWord,int currentPage,int totalItemsPerPage, String order) {
        Session session=getSession();
        Query query=session.createQuery("from Post p where p.title like '%"+keyWord+"%' order by p.sendTime "+order);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List posts=query.list();
        return posts;
    }

    @Override
    public int getSearchResultNum(String keyWord, String order) {
        Session session=getSession();
        Query query=session.createQuery("from Post p where p.title like '%"+keyWord+"%' order by p.sendTime "+order);
        List posts=query.list();
        return posts.size();
    }
}
