package dao;

import vo.SubForum;
import java.util.List;

public interface SubForumDAO {
    public List getSubForumsByMainForumId(int mainForumId);
    public SubForum getSubForumById(int id);
    public void addSubForum(SubForum subForum);
    public void updateSubForum(SubForum subForum);
    public void deleteSubForum(SubForum subForum);
}
