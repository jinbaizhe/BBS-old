package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.UserService;
import vo.User;

import java.util.Map;

public class UserAction extends ActionSupport {
    private User user;
    private UserService userService;
    private String message_info;
    private String message_password;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getMessage_info() {
        return message_info;
    }

    public void setMessage_info(String message_info) {
        this.message_info = message_info;
    }

    public String getMessage_password() {
        return message_password;
    }

    public void setMessage_password(String message_password) {
        this.message_password = message_password;
    }

    public String getSettingPage()
    {
        user=getSessionUser();
        return SUCCESS;
    }
    public String updateUserInfo()
    {
        User session_user=getSessionUser();
        user.setId(session_user.getId());
        userService.updateUserInfo(user);
        message_info="修改信息成功";
        return SUCCESS;
    }

    public String updateUserPassword()
    {
        User session_user=getSessionUser();
        if(!user.getPassword().equals(session_user.getPassword()))
            message_password="修改失败：旧密码输入错误";
        else
         {
             user.setId(session_user.getId());
             userService.updateUserPassword(user);
             message_password="修改密码成功";
        }
        return SUCCESS;
    }

    public User getSessionUser()
    {
        Map session= ActionContext.getContext().getSession();
        return (User)session.get("user");
    }
}
