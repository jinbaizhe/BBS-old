package service;

import vo.Collection;
import vo.User;

import java.util.List;

public interface UserService {
    public User validateUser(String username, String password);
    public void createUser(User user) throws Exception;
    public void updateUser(User user);
    public boolean isExistUser(String username);
    public List getAllUsersExceptSelf(User user,int currentPage,int totalItemsPerPage);
    public int getAllUsersNumExceptSelf(User user);
    public void updateUserPassword(User user);
    public void updateUserInfo(User user);
    public void setAdmin(int userid);
    public void unsetAdmin(int userid);
    public User getUserByid(int userid);
    public List getCollectionsByUserId(int userid,int currentPage,int totalItemsPerPage,String order);
    public void createCollection(Collection collection);
    public void deleteCollection(int userid, int postid);
    public Collection getCollection(int userid, int postid);
}
