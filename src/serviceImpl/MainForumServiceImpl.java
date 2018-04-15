package serviceImpl;

import dao.MainForumDAO;
import service.MainForumService;
import util.Util;
import vo.MainForum;

import java.sql.Timestamp;
import java.util.List;

public class MainForumServiceImpl implements MainForumService {
    private MainForumDAO mainForumDAO;

    public MainForumDAO getMainForumDAO() {
        return mainForumDAO;
    }

    public void setMainForumDAO(MainForumDAO mainForumDAO) {
        this.mainForumDAO = mainForumDAO;
    }

    @Override
    public List getAllMainForums() {
        return mainForumDAO.getAllMainForums();
    }

    @Override
    public MainForum getMainForumById(int id) {
        return mainForumDAO.getMainForumById(id);
    }

    @Override
    public void addMainForum(MainForum mainForum) {
        mainForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        mainForumDAO.addMainForum(mainForum);
    }

    @Override
    public void updateMainForum(MainForum mainForum) {
        MainForum temp_mainForum=getMainForumById(mainForum.getId());
        temp_mainForum.setName(mainForum.getName());
        temp_mainForum.setInfo(mainForum.getInfo());
        mainForumDAO.updateMainForum(temp_mainForum);
    }

    @Override
    public void deleteMainForum(MainForum mainForum) {
        mainForumDAO.deleteMainForum(mainForum);
    }
}
