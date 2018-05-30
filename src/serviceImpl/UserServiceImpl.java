package serviceImpl;

import dao.CollectionDAO;
import dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserService;
import util.Util;
import vo.Collection;
import vo.User;

import java.sql.Timestamp;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private CollectionDAO collectionDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public CollectionDAO getCollectionDAO() {
        return collectionDAO;
    }

    public void setCollectionDAO(CollectionDAO collectionDAO) {
        this.collectionDAO = collectionDAO;
    }

    @Override
    public User validateUser(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    @Override
    public void createUser(User user) throws Exception {
        user.setLevel(0);
        user.setStatus(0);
        user.setType(0);
        user.setRegisterTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        String key= "1234567890abcdefghijklmnopqrstuvwxyz";
        String value="";
        for(int i=0;i<20;i++)
            value+=key.charAt( (int)( Math.random()*key.length() ) );
        user.setActiveKey(value);
        Session session=userDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            if(userDAO.isExistUser(user.getUsername()))
                throw new Exception("用户名已被注册");
            userDAO.createUser(user);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void updateUser(User user) {
        Session session=userDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            userDAO.updateUser(user);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public boolean isExistUser(String username) {
        return userDAO.isExistUser(username);
    }

    @Override
    public List getAllUsersExceptSelf(User user,int currentPage,int totalItemsPerPage) {
        return userDAO.getAllUsersExceptSuperAdmin(user,currentPage,totalItemsPerPage);
    }

    @Override
    public int getAllUsersNumExceptSelf(User user) {
        return userDAO.getAllUsersExceptSuperAdmin(user).size();
    }

    @Override
    public void updateUserPassword(User user) {
        User temp_user=userDAO.getUser(user.getId());
        temp_user.setPassword(user.getPassword());
        updateUser(temp_user);
    }

    @Override
    public void updateUserInfo(User user) {
        User temp_user=userDAO.getUser(user.getId());
        if(!user.getEmail().equals(temp_user.getEmail()))
            temp_user.setStatus(0);
        temp_user.setEmail(user.getEmail());
        temp_user.setInfo(user.getInfo());
        temp_user.setSex(user.getSex());
        updateUser(temp_user);

    }

    @Override
    public void setAdmin(int userid) {
        User temp_user=userDAO.getUser(userid);
        temp_user.setType(1);
        updateUser(temp_user);
    }

    @Override
    public void unsetAdmin(int userid) {
        User temp_user=userDAO.getUser(userid);
        temp_user.setType(0);
        updateUser(temp_user);
    }

    @Override
    public User getUserByid(int userid) {
        return userDAO.getUser(userid);
    }

    @Override
    public List getCollectionsByUserId(int userid, int currentPage, int totalItemsPerPage, String order) {
        return collectionDAO.getCollectionsByUserId(userid,currentPage,totalItemsPerPage,order) ;
    }

    @Override
    public void createCollection(Collection collection) {
        Session session=userDAO.getSession();
        Transaction transaction=session.beginTransaction();
        collectionDAO.createCollection(collection);
        session.flush();
        transaction.commit();
    }

    @Override
    public void deleteCollection(int userid, int postid)
    {
        Session session=userDAO.getSession();
        Transaction transaction=session.beginTransaction();
        collectionDAO.deleteCollection(userid,postid);
        session.flush();
        transaction.commit();
    }

    @Override
    public Collection getCollection(int userid, int postid) {
        return collectionDAO.getCollection(userid,postid);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
