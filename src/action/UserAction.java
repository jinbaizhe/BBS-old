package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.FollowpostService;
import service.PostService;
import service.UserService;
import vo.User;

import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {
    private User user;
    private UserService userService;
    private PostService postService;
    private FollowpostService followpostService;
    private String message_info;
    private String message_password;
    private String type="info";
    private List posts;
    private List followposts;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }

    public List getFollowposts() {
        return followposts;
    }

    public void setFollowposts(List followposts) {
        this.followposts = followposts;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public FollowpostService getFollowpostService() {
        return followpostService;
    }

    public void setFollowpostService(FollowpostService followpostService) {
        this.followpostService = followpostService;
    }

    private User getSessionUser()
    {
        Map session= ActionContext.getContext().getSession();
        return (User)session.get("user");
    }

    public String getSettingPageNeedUserLogin()
    {
        user=getSessionUser();
        posts=postService.getPostsByUserId(user.getId());
        followposts=followpostService.getFollowpostsByUserId(user.getId());
        return SUCCESS;
    }
    public String updateUserInfoNeedUserLogin()
    {
        User session_user=getSessionUser();
        user.setId(session_user.getId());
        userService.updateUserInfo(user);
        message_info="修改信息成功";
        getSettingPageNeedUserLogin();
        type="info";
        //user更新保存到session中
        Map session= ActionContext.getContext().getSession();
        session.put("user",userService.getUserByid(user.getId()));
        return SUCCESS;
    }

    public String updateUserPasswordNeedUserLogin()
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
        getSettingPageNeedUserLogin();
        type="password";
        //user更新保存到session中
        Map session= ActionContext.getContext().getSession();
        session.put("user",userService.getUserByid(user.getId()));
        return SUCCESS;
    }



}
