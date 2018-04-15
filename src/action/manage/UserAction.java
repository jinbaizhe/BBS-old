package action.manage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.UserService;
import util.Num;
import util.Pager;
import vo.User;

import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {
    private List users;
    private UserService userService;
    private int page=1;
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

    public String getUserPage()
    {
        Map session=(Map)ActionContext.getContext().getSession();
        User user=(User)session.get("user");
        users=userService.getAllUsersExceptSelf(user,page,Num.ShowManageUserPerPageNum.getValue());
        int totalUsersNum=userService.getAllUsersNumExceptSelf(user);
        Map request=(Map) ActionContext.getContext().get("request");
        Pager pager=new Pager(page, Num.ShowManageUserPerPageNum.getValue(),totalUsersNum);
        request.put("pager",pager);
        return SUCCESS;
    }
}
