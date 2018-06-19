package daoImpl;

import dao.BaseDAO;
import dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vo.User;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createUser(User user) {
        create(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateUser(User user) {
        update(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteUser(User user) {
        delete(user);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List getAllUsersExceptSuperAdmin(User user) {
        Session session=getSession();
        String sql="from User u where u.type<2 order by u.registerTime desc ";
        Query query=session.createQuery(sql);
        List users=query.list();
//        session.close();
        return users;
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
