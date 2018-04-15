package dao;

import vo.MainForum;

import java.util.List;

public interface MainForumDAO {
    public List getAllMainForums();
    public MainForum getMainForumById(int id);
    public void addMainForum(MainForum mainForum);
    public void updateMainForum(MainForum mainForum);
    public void deleteMainForum(MainForum mainForum);
}
