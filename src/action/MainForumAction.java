package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.MainForumService;
import service.SubForumService;
import vo.MainForum;
import java.util.*;
@Controller("mainForumAction")
@Scope("prototype")
public class MainForumAction extends ActionSupport {
    @Autowired
    private MainForumService mainForumService;
    @Autowired
    private SubForumService subForumService;
    private int page;
    private int mfid;
    private Map mainForumMap=new LinkedHashMap();

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Map getMainForumMap() {
        return mainForumMap;
    }

    public void setMainForumMap(Map mainForumMap) {
        this.mainForumMap = mainForumMap;
    }

    public String browserMainForum()
    {
        Map session = ActionContext.getContext().getSession();
        if(mfid!=0)
        {
            mainForumMap.put(mainForumService.getMainForumById(mfid),subForumService.getSubForumsByMainForumId(mfid));
            return "sub";
        }
        else {
            List<MainForum> mainForums=mainForumService.getAllMainForums();
            for(MainForum mainForum:mainForums)
            {
                int mainForumId=mainForum.getId();
                mainForumMap.put(mainForum,subForumService.getSubForumsByMainForumId(mainForumId));
            }
            return "all";
        }
    }
}
