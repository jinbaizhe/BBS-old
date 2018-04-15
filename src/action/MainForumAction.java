package action;

import com.opensymphony.xwork2.ActionSupport;
import service.MainForumService;
import service.SubForumService;
import vo.MainForum;

import java.util.*;

public class MainForumAction extends ActionSupport {
    private MainForumService mainForumService;
    private SubForumService subForumService;
    private List forums;
    private int page;
    private int mfid;
    private MainForum mainForum;

    public MainForumService getMainForumService() {
        return mainForumService;
    }

    public void setMainForumService(MainForumService mainForumService) {
        this.mainForumService = mainForumService;
    }

    public SubForumService getSubForumService() {
        return subForumService;
    }

    public void setSubForumService(SubForumService subForumService) {
        this.subForumService = subForumService;
    }

    public int getMfid() {
        return mfid;
    }

    public void setMfid(int mfid) {
        this.mfid = mfid;
    }

    public List getForums() {
        return forums;
    }

    public void setForums(List forums) {
        this.forums = forums;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public MainForum getMainForum() {
        return mainForum;
    }

    public void setMainForum(MainForum mainForum) {
        this.mainForum = mainForum;
    }

    public String browserMainForum()
    {
        if(mfid!=0)
        {
            forums=subForumService.getSubForumsByMainForumId(mfid);
            mainForum=mainForumService.getMainForumById(mfid);
            return "sub";
        }
        else {
            forums=mainForumService.getAllMainForums();
            return "all";
        }
    }
}
