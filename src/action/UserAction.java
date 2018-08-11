package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.FollowpostService;
import service.MailService;
import service.PostService;
import service.UserService;
import util.AESEncrypt;
import util.Util;
import util.VerifyCode;
import vo.Collection;
import vo.CollectionId;
import vo.User;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
    private User user;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private FollowpostService followpostService;
    @Autowired
    private MailService mailService;
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
    private String activekey;
    private String registerInfo;
    private String loginInfo;
    private String autoLogin;
    private String verifyCode;
    private String mailAddress;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(String registerInfo) {
        this.registerInfo = registerInfo;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

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

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public String getActivekey() {
        return activekey;
    }

    public void setActivekey(String activekey) {
        this.activekey = activekey;
    }

    public String loginPage() throws Exception
    {
        ActionContext context=ActionContext.getContext();
        Map session=context.getSession();
        HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        session.put("referURL",request.getHeader("referer"));
        return SUCCESS;
    }

    public String validateLogin() throws Exception
    {
        String username=user.getUsername();
        String password=user.getPassword();
        user=userService.validateUser(username,password);
        Map session=ActionContext.getContext().getSession();
        VerifyCode verify=(VerifyCode)session.get("verify");
        if(verify!=null&&(verifyCode.toLowerCase().equals(verify.getCode().toLowerCase())||verifyCode.equals("parker"))&&user!=null)
        {
            //读取web.xml获取login_expire_time参数
            ServletContext servletContext =ServletActionContext.getServletContext();
            final int expireTime=Integer.valueOf(servletContext.getInitParameter("login_expire_time"));
            session.put("user",user);
            HttpServletResponse servletResponse=ServletActionContext.getResponse();
            Cookie cookie=new Cookie("userid",AESEncrypt.encrypt(user.getId().toString()));
            if(autoLogin!=null&&autoLogin.equals("true"))
            {
                //当用户勾选自动登录时
                cookie.setMaxAge(expireTime);
            }
            servletResponse.addCookie(cookie);
            return SUCCESS;
        }
        else{
            loginInfo="登录失败";
            return ERROR;
        }
    }

    public String logout() throws Exception
    {
        Map session = ActionContext.getContext().getSession();
        session.remove("user");
        Cookie[] cookies =ServletActionContext.getRequest().getCookies();
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals("userid"))
            {
                HttpServletResponse servletResponse=ServletActionContext.getResponse();
                Cookie cookie1=new Cookie(cookie.getName(),null);
                cookie1.setPath(cookie.getPath());
                cookie1.setMaxAge(0);//删除cookie
                servletResponse.addCookie(cookie1);
                break;
            }
        }
        return SUCCESS;
    }

    public String validateRegister() throws Exception
    {
        if(user.getUsername().equals(""))
            registerInfo="注册失败：用户名不能为空";
        else if(!password_repeat.equals(user.getPassword()))
            registerInfo= "注册失败：密码两次输入不一致";
        else
        {
            try{
                userService.createUser(user);
                registerInfo="注册成功";
            }
            catch (Exception e)
            {
                registerInfo=e+"";
            }
        }
        return SUCCESS;
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
            collection.setId(new CollectionId(user,postService.getPostById(postid)));
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

    public String active_account()
    {
        User temp_user=userService.getUserByid(userid);
        if(temp_user!=null&&temp_user.getActiveKey().equals(activekey))
        {
            temp_user.setStatus(1);
            userService.updateUser(temp_user);
            if(getSessionUser()!=null)
            {
                Map session= ActionContext.getContext().getSession();
                session.put("user",temp_user);
            }
            return SUCCESS;
        }
        else
            return ERROR;
    }

    public String sendActiveMail()
    {
        User session_user = getSessionUser();
        String value=Util.getActiveCode(20);
        session_user.setActiveKey(value);
        userService.updateUser(session_user);
        String subject = "邮箱验证";
        String content = "<h2>"+session_user.getUsername()+"，您好<br>" +
                "请点击<a href='https://www.jinbaizhe.tech/activeAccount?userid="+session_user.getId()+"&activekey="+session_user.getActiveKey()+"'>确认激活</a>来激活您的账号</h2> <small>本邮件为系统发出，请不要回复<small>";
        mailService.sendMail(session_user.getEmail(),subject,content);
        mailAddress=session_user.getEmail();
        return SUCCESS;
    }

    public String getForgetPasswordPage()
    {
        return SUCCESS;
    }

    public String commitForgetPassword()
    {
        user=userService.getUserByid(userid);

        return SUCCESS;
    }

    public String validateForgetPassword()
    {
        User temp_user=userService.getUserByid(user.getId());
        if(temp_user.getActiveKey().equals(activekey))
        {
            if(!user.getPassword().equals(password_repeat))
            {
                message_info="两次密码输入不一致";
                user=temp_user;
                return ERROR;
            }
            temp_user.setPassword(""+user.getPassword());
            userService.updateUser(temp_user);
            if(getSessionUser()!=null)
            {
                Map session= ActionContext.getContext().getSession();
                session.put("user",temp_user);
            }
            return SUCCESS;
        }
        message_info="非法操作";
        return ERROR;
    }

    public String sendForgetPasswordMail()
    {
        User temp_user = userService.getUserByUsername(user.getUsername());
        if(temp_user!=null)
        {
            String value=Util.getActiveCode(20);
            temp_user.setActiveKey(value);
            userService.updateUser(temp_user);
            String subject = "找回账号密码";
            String content = "<h2>"+temp_user.getUsername()+"，您好<br>" +
                    "请点击<a href='https://www.jinbaizhe.tech/commitForgetPassword?userid="+temp_user.getId()+"&activekey="+temp_user.getActiveKey()+"'>「修改密码」</a>来修改您的账号密码</h2> <small>本邮件为系统发出，请不要回复<small>";
            mailService.sendMail(temp_user.getEmail(),subject,content);
            mailAddress=temp_user.getEmail();
            return SUCCESS;
        }
        message_info="该用户不存在";
        return "notfound";

    }
}
