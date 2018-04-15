package serviceImpl;

import dao.SubForumDAO;
import service.SubForumService;
import util.Util;
import vo.SubForum;

import java.sql.Timestamp;
import java.util.List;

public class SubForumServiceImpl implements SubForumService {
    private SubForumDAO subForumDAO;

    public SubForumDAO getSubForumDAO() {
        return subForumDAO;
    }

    public void setSubForumDAO(SubForumDAO subForumDAO) {
        this.subForumDAO = subForumDAO;
    }

    @Override
    public List getSubForumsByMainForumId(int mainForumID) {
        return subForumDAO.getSubForumsByMainForumId(mainForumID);
    }

    @Override
    public SubForum getSubForumById(int id) {
        return subForumDAO.getSubForumById(id);
    }

    @Override
    public void addSubForum(SubForum subForum) {
        subForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        subForumDAO.addSubForum(subForum);
    }

    @Override
    public void updateSubForum(SubForum subForum) {
        SubForum temp_subForum=getSubForumById(subForum.getId());
        temp_subForum.setName(subForum.getName());
        temp_subForum.setInfo(subForum.getInfo());
        subForumDAO.updateSubForum(temp_subForum);
    }

    @Override
    public void deleteSubForum(SubForum subForum) {
        subForumDAO.deleteSubForum(subForum);
    }
}
