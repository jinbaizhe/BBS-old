package serviceImpl;

import dao.CollectionDAO;
import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import util.AESEncrypt;
import util.Util;
import vo.Collection;
import vo.User;
import java.sql.Timestamp;
import java.util.List;

@Component("userService")
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private CollectionDAO collectionDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public CollectionDAO getCollectionDAO() {
        return collectionDAO;
    }

    @Autowired
    public void setCollectionDAO(CollectionDAO collectionDAO) {
        this.collectionDAO = collectionDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public User validateUser(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createUser(User user) throws Exception {
        if(userDAO.isExistUser(user.getUsername()))
            throw new Exception("用户名已被注册");
        user.setLevel(0);
        user.setStatus(0);
        user.setType(0);
        user.setRegisterTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        String value=AESEncrypt.encrypt(user.getUsername()).substring(0,20);
        user.setActiveKey(value);
        userDAO.createUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExistUser(String username) {
        return userDAO.isExistUser(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllUsersExceptSelf(User user,int currentPage,int totalItemsPerPage) {
        return userDAO.getAllUsersExceptSuperAdmin(user,currentPage,totalItemsPerPage);
    }

    @Override
    @Transactional(readOnly = true)
    public int getAllUsersNumExceptSelf(User user) {
        return userDAO.getAllUsersExceptSuperAdmin(user).size();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateUserPassword(User user) {
        User temp_user=userDAO.getUser(user.getId());
        temp_user.setPassword(user.getPassword());
        updateUser(temp_user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
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
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void setAdmin(int userid) {
        User temp_user=userDAO.getUser(userid);
        temp_user.setType(1);
        updateUser(temp_user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void unsetAdmin(int userid) {
        User temp_user=userDAO.getUser(userid);
        temp_user.setType(0);
        updateUser(temp_user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByid(int userid) {
        return userDAO.getUser(userid);
    }

    @Override
    @Transactional(readOnly = true)
    public List getCollectionsByUserId(int userid, int currentPage, int totalItemsPerPage, String order) {
        return collectionDAO.getCollectionsByUserId(userid,currentPage,totalItemsPerPage,order) ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void createCollection(Collection collection) {
        collectionDAO.createCollection(collection);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteCollection(int userid, int postid)
    {
        collectionDAO.deleteCollection(userid,postid);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection getCollection(int userid, int postid) {
        return collectionDAO.getCollection(userid,postid);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
