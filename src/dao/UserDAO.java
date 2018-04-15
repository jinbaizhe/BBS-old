package dao;

import vo.User;

import java.util.List;

public interface UserDAO  {
    public User validateUser(String username, String password);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public boolean isExistUser(String username);
    public List getAllUsersExceptSelf(User user,int currentPage,int totalItemsPerPage);
    public List getAllUsersExceptSelf(User user);
    public User getUser(int id);
}
