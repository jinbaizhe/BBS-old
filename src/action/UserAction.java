package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.FollowpostService;
import service.PostService;
import service.UserService;
import util.Util;
import vo.Collection;
import vo.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
    private String password_old;
    private String password_repeat;
    private List collections;
    private int userid;
    private String order="desc";
    private int page;
    private int postid;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List getCollections() {
        return collections;
    }

    public void setCollections(List collections) {
        this.collections = collections;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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

    public String getPassword_old() {
        return password_old;
    }

    public void setPassword_old(String password_old) {
        this.password_old = password_old;
    }

    public String getPassword_repeat() {
        return password_repeat;
    }

    public void setPassword_repeat(String password_repeat) {
        this.password_repeat = password_repeat;
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

        //读取web.xml获取ShowPostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowCollectionsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowCollectionsPerPageNum"));

        collections=userService.getCollectionsByUserId(user.getId(),page,ShowCollectionsPerPageNum,order);
        return SUCCESS;
    }

    public String getUserInfoPage()
    {
        user=userService.getUserByid(userid);
        posts=postService.getPostsByUserId(user.getId());
        followposts=followpostService.getFollowpostsByUserId(user.getId());

        //读取web.xml获取ShowPostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowCollectionsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowCollectionsPerPageNum"));

        collections=userService.getCollectionsByUserId(user.getId(),page,ShowCollectionsPerPageNum,order);
        return SUCCESS;
    }

    public String updateUserInfoNeedUserLogin()
    {
        User session_user=getSessionUser();
        user.setId(session_user.getId());
        userService.updateUserInfo(user);
        message_info="修改信息成功";
        user=userService.getUserByid(user.getId());
        //user更新保存到session中
        Map session= ActionContext.getContext().getSession();
        session.put("user",user);
        getSettingPageNeedUserLogin();
        type="info";
        return SUCCESS;
    }

    public String updateUserPasswordNeedUserLogin()
    {
        User session_user=getSessionUser();
        if(!password_repeat.equals(user.getPassword()))
            message_password="修改失败：两次密码输入不一致";
        else if(!password_old.equals(session_user.getPassword()))
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

    public String starPostNeedUserLogin() throws Exception
    {
        Map session=ActionContext.getContext().getSession();
        ActionContext context=ActionContext.getContext();
        HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        session.put("referURL",request.getHeader("referer"));
        User user=(User)session.get("user");
        if(userService.getCollection(user.getId(),postid)==null)
        {
            Collection collection=new Collection();
            collection.setUser(user);
            collection.setPost(postService.getPostById(postid));
            collection.setTime(Timestamp.valueOf(Util.getCurrentDateTime()));
            userService.createCollection(collection);
        }
        return SUCCESS;
    }
    public String unstarPostNeedUserLogin() throws Exception
    {
        Map session=ActionContext.getContext().getSession();
        ActionContext context=ActionContext.getContext();
        HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        session.put("referURL",request.getHeader("referer"));
        User user=(User)session.get("user");
        userService.deleteCollection(user.getId(),postid);
        return SUCCESS;
    }

}
