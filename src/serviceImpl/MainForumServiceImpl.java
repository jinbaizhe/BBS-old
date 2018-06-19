package serviceImpl;

import dao.MainForumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.MainForumService;
import util.Util;
import vo.MainForum;
import java.sql.Timestamp;
import java.util.List;

@Component("mainForumService")
public class MainForumServiceImpl implements MainForumService {
    private MainForumDAO mainForumDAO;

    public MainForumDAO getMainForumDAO() {
        return mainForumDAO;
    }

    @Autowired
    public void setMainForumDAO(MainForumDAO mainForumDAO) {
        this.mainForumDAO = mainForumDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllMainForums() {
        return mainForumDAO.getAllMainForums();
    }

    @Override
    @Transactional(readOnly = true)
    public MainForum getMainForumById(int id) {
        return mainForumDAO.getMainForumById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addMainForum(MainForum mainForum) {
        mainForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        mainForumDAO.addMainForum(mainForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateMainForum(MainForum mainForum) {
        MainForum temp_mainForum=getMainForumById(mainForum.getId());
        temp_mainForum.setName(mainForum.getName());
        temp_mainForum.setInfo(mainForum.getInfo());
        mainForumDAO.updateMainForum(temp_mainForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteMainForum(MainForum mainForum) {
        mainForumDAO.deleteMainForum(mainForum);
    }
}
