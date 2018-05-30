package daoImpl;

import dao.BaseDAO;
import dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vo.User;

import java.util.List;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public User validateUser(String username, String password) {
        Session session=getSession();
        String sql="from User u where u.username=? and u.password=?";
        Query query=session.createQuery(sql);
        query.setParameter(0, username);
        query.setParameter(1, password);
        List users=query.list();
//        session.close();
        User user=null;
        if(users.size()!=0)
            user=(User)users.get(0);
        return user;
    }

    @Override
    public void createUser(User user) {
        create(user);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public void deleteUser(User user) {
        delete(user);
    }

    @Override
    public boolean isExistUser(String username) {
        Session session=getSession();
        String sql="from User u where u.username=?";
        Query query=session.createQuery(sql);
        query.setParameter(0, username);
        List users=query.list();
//        session.close();
        if (users.size()!=0)
            return true;
        else
            return false;
    }

    @Override
    public List getAllUsersExceptSuperAdmin(User user,int currentPage,int totalItemsPerPage) {
        Session session=getSession();
        String sql="from User u where u.type<2 order by u.registerTime desc ";
        Query query=session.createQuery(sql);
        query.setFirstResult((currentPage-1)*totalItemsPerPage);
        query.setMaxResults(totalItemsPerPage);
        List users=query.list();
//        session.close();
        return users;
    }

    @Override
    public List getAllUsersExceptSuperAdmin(User user) {
        Session session=getSession();
        String sql="from User u where u.type<2 order by u.registerTime desc ";
        Query query=session.createQuery(sql);
        List users=query.list();
//        session.close();
        return users;
    }

    @Override
    public User getUser(int id) {
        Session session=getSession();
        Query query=session.createQuery("from User u where u.id=?");
        query.setParameter(0,id);
        List users=query.list();
//        session.close();
        User user=null;
        if(users.size()!=0)
            user=(User)users.get(0);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session=getSession();
        Query query=session.createQuery("from User u where u.username=?");
        query.setParameter(0,username);
        List users=query.list();
//        session.close();
        User user=null;
        if(users.size()!=0)
            user=(User)users.get(0);
        return user;
    }
}
