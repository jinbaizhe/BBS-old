package serviceImpl;

import dao.UserDAO;
import service.UserService;
import util.Util;
import vo.User;

import java.sql.Timestamp;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User validateUser(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    @Override
    public void createUser(User user) {
        user.setLevel(0);
        user.setStatus(0);
        user.setType(0);
        user.setRegisterTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        userDAO.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
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
        userDAO.updateUser(temp_user);
    }

    @Override
    public void updateUserInfo(User user) {
        User temp_user=userDAO.getUser(user.getId());
        temp_user.setEmail(user.getEmail());
        temp_user.setInfo(user.getInfo());
        temp_user.setSex(user.getSex());
        userDAO.updateUser(temp_user);
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


}
