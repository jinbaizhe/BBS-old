package dao;

import vo.Collection;

import java.util.List;

public interface CollectionDAO {
    public List getCollectionsByUserId(int userid,int currentPage,int totalItemsPerPage,String order);
    public List getCollectionsByPostId(int postid);
    public void createCollection(Collection collection);
    public void deleteCollection(int userid,int postid);
    public Collection getCollection(int userid,int postid);
}
