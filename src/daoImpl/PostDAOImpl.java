package daoImpl;

import dao.BaseDAO;
import dao.PostDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vo.Post;
import java.util.List;

@Repository("postDAO")
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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createPost(Post post) {
        create(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updatePost(Post post) {
        update(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deletePost(Post post) {
        delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public int getPostsNumBySubForumId(int subForumId) {
        Session session=getSession();
        String sql="select count(*) from Post p where p.subForum.id=?";
        Query query=session.createQuery(sql);
        query.setParameter(0,subForumId);
//        session.close();
        return ((Long)query.list().get(0)).intValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List getPostsByUserId(int userid,String order) {
        Session session=getSession();
        Query query=session.createQuery("from Post p where p.user.id=? order by "+order);
        query.setParameter(0,userid);
        List posts=query.list();
//        session.close();
        return posts;
    }

    @Override
    @Transactional(readOnly = true)
    public List getSearchResult(String keyWord,int currentPage,int totalItemsPerPage, String order) {
        Session session=getSession();
        Query query=session.createQuery("from Post p where p.title like '%"+keyWord+"%' or p.content like '%"+keyWord+"%' order by p.sendTime "+order);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List posts=query.list();
        return posts;
    }

    @Override
    @Transactional(readOnly = true)
    public int getSearchResultNum(String keyWord, String order) {
        Session session=getSession();
        Query query=session.createQuery("select count(*) from Post p where p.title like '%"+keyWord+"%' order by p.sendTime "+order);
        return ((Long)query.list().get(0)).intValue();
    }
}
