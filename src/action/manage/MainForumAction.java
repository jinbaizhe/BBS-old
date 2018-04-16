package action.manage;

import com.opensymphony.xwork2.ActionSupport;
import service.MainForumService;
import vo.MainForum;

import java.util.List;

public class MainForumAction extends ActionSupport {
    private MainForumService mainForumService;
    private List mainForums;
    private MainForum mainForum;
    private int mfid;
    public MainForumService getMainForumService() {
        return mainForumService;
    }

    public void setMainForumService(MainForumService mainForumService) {
        this.mainForumService = mainForumService;
    }

    public List getMainForums() {
        return mainForums;
    }

    public void setMainForums(List mainForums) {
        this.mainForums = mainForums;
    }

    public MainForum getMainForum() {
        return mainForum;
    }

    public void setMainForum(MainForum mainForum) {
        this.mainForum = mainForum;
    }

    public int getMfid() {
        return mfid;
    }

    public void setMfid(int mfid) {
        this.mfid = mfid;
    }

    public String getIndexPageNeedUserLoginNeedManageLogin()
    {
        return SUCCESS;
    }

    public String getAllMainForumPageNeedUserLoginNeedManageLogin()
    {
        mainForums=mainForumService.getAllMainForums();
        return SUCCESS;
    }

    public String commitAddMainForumNeedUserLoginNeedManageLogin()
    {
        mainForumService.addMainForum(mainForum);
        return SUCCESS;
    }

    public String getUpdateMainForumPageNeedUserLoginNeedManageLogin()
    {
        mainForum=mainForumService.getMainForumById(mfid);
        return SUCCESS;
    }

    public String commitUpdateMainForumNeedUserLoginNeedManageLogin()
    {
        mainForumService.updateMainForum(mainForum);
        return SUCCESS;
    }

    public String commitDeleteMainForumNeedUserLoginNeedManageLogin()
    {
        mainForumService.deleteMainForum(mainForumService.getMainForumById(mfid));
        return SUCCESS;
    }
}
