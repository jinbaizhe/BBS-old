package action.manage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import util.Pager;
import vo.User;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {
    private List users;
    private UserService userService;
    private int page=1;
    private int userid;

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserPageNeedUserLoginNeedManageLoginNeedSuperAdmin()
    {
        Map session=(Map)ActionContext.getContext().getSession();
        User user=(User)session.get("user");

        //读取web.xml获取ShowFollowpostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowManageUserPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowManageUserPerPageNum"));

        users=userService.getAllUsersExceptSelf(user,page,ShowManageUserPerPageNum);
        int totalUsersNum=userService.getAllUsersNumExceptSelf(user);
        Map request=(Map) ActionContext.getContext().get("request");
        Pager pager=new Pager(page, ShowManageUserPerPageNum,totalUsersNum);
        request.put("pager",pager);
        return SUCCESS;
    }

    public String setAdminNeedUserLoginNeedManageLoginNeedSuperAdmin()
    {
        userService.setAdmin(userid);
        return SUCCESS;
    }

    public String unsetAdminNeedUserLoginNeedManageLoginNeedSuperAdmin()
    {
        userService.unsetAdmin(userid);
        return SUCCESS;
    }
}
