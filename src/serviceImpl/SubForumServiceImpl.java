package serviceImpl;

import dao.SubForumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SubForumService;
import util.Util;
import vo.SubForum;
import java.sql.Timestamp;
import java.util.List;

@Component("subForumService")
public class SubForumServiceImpl implements SubForumService {
    private SubForumDAO subForumDAO;

    public SubForumDAO getSubForumDAO() {
        return subForumDAO;
    }

    @Autowired
    public void setSubForumDAO(SubForumDAO subForumDAO) {
        this.subForumDAO = subForumDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List getSubForumsByMainForumId(int mainForumID) {
        return subForumDAO.getSubForumsByMainForumId(mainForumID);
    }

    @Override
    @Transactional(readOnly = true)
    public SubForum getSubForumById(int id) {
        return subForumDAO.getSubForumById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void addSubForum(SubForum subForum) {
        subForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        subForumDAO.addSubForum(subForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void updateSubForum(SubForum subForum) {
        SubForum temp_subForum=getSubForumById(subForum.getId());
        temp_subForum.setName(subForum.getName());
        temp_subForum.setInfo(subForum.getInfo());
        subForumDAO.updateSubForum(temp_subForum);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteSubForum(SubForum subForum) {
        subForumDAO.deleteSubForum(subForum);
    }
}
