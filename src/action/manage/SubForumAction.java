package action.manage;

import com.opensymphony.xwork2.ActionSupport;
import service.MainForumService;
import service.SubForumService;
import sun.applet.Main;
import vo.MainForum;
import vo.SubForum;

import java.util.List;

public class SubForumAction extends ActionSupport {
    private SubForumService subForumService;
    private MainForumService mainForumService;
    private int mfid;
    private int sfid;
    private List subForums;
    private SubForum subForum;
    private MainForum mainForum;
    public SubForumService getSubForumService() {
        return subForumService;
    }

    public void setSubForumService(SubForumService subForumService) {
        this.subForumService = subForumService;
    }

    public MainForumService getMainForumService() {
        return mainForumService;
    }

    public void setMainForumService(MainForumService mainForumService) {
        this.mainForumService = mainForumService;
    }

    public int getMfid() {
        return mfid;
    }

    public void setMfid(int mfid) {
        this.mfid = mfid;
    }

    public List getSubForums() {
        return subForums;
    }

    public void setSubForums(List subForums) {
        this.subForums = subForums;
    }

    public int getSfid() {
        return sfid;
    }

    public void setSfid(int sfid) {
        this.sfid = sfid;
    }

    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    public MainForum getMainForum() {
        return mainForum;
    }

    public void setMainForum(MainForum mainForum) {
        this.mainForum = mainForum;
    }

    public String getSubForumsByMainForumId()
    {
        subForums=subForumService.getSubForumsByMainForumId(mfid);
        return SUCCESS;
    }
    public String getAddSubForumPage()
    {
        mainForum=mainForumService.getMainForumById(mfid);
        return SUCCESS;
    }
    public String commitAddSubForum()
    {
        subForum.setMainForum(mainForumService.getMainForumById(mfid));
        subForumService.addSubForum(subForum);
        return SUCCESS;
    }

    public String getUpdateSubForumPage()
    {
        subForum=subForumService.getSubForumById(sfid);
        return SUCCESS;
    }

    public String commitUpdateSubForum()
    {
        subForum.setMainForum(mainForumService.getMainForumById(mfid));
        subForumService.updateSubForum(subForum);
        return SUCCESS;
    }

    public String commitDeleteSubForum()
    {
        subForumService.deleteSubForum(subForumService.getSubForumById(sfid));
        return SUCCESS;
    }
}
