package serviceImpl;

import dao.SubForumDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        Session session=subForumDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            subForumDAO.addSubForum(subForum);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void updateSubForum(SubForum subForum) {
        SubForum temp_subForum=getSubForumById(subForum.getId());
        temp_subForum.setName(subForum.getName());
        temp_subForum.setInfo(subForum.getInfo());
        Session session=subForumDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            subForumDAO.updateSubForum(temp_subForum);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deleteSubForum(SubForum subForum) {
        Session session=subForumDAO.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            subForumDAO.deleteSubForum(subForum);
            session.flush();
            transaction.commit();
        }catch (Exception e)
        {
            transaction.rollback();
            throw e;
        }
    }
}
