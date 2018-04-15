package service;

import vo.SubForum;

import java.util.List;

public interface SubForumService {
    public List getSubForumsByMainForumId(int mainForumID);
    public SubForum getSubForumById(int id);
    public void addSubForum(SubForum subForum);
    public void updateSubForum(SubForum subForum);
    public void deleteSubForum(SubForum subForum);
}
