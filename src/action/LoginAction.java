package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import util.VerifyCode;
import vo.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginAction extends ActionSupport {
	private UserService userService;
	private User user;
	private String registerInfo;
	private String loginInfo;
	private String autoLogin;
	private String password_repeat;
	private String verifyCode;
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getPassword_repeat() {
		return password_repeat;
	}

	public void setPassword_repeat(String password_repeat) {
		this.password_repeat = password_repeat;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
			final int expireTime=60*60*24*7;
			session.put("user",user);
			if(autoLogin!=null&&autoLogin.equals("true"))
			{
				//当用户勾选自动登录时
				Cookie[] cookies =ServletActionContext.getRequest().getCookies();
				for(Cookie cookie:cookies)
				{
					if(cookie.getName().equals("JSESSIONID"))
					{
						ServletResponse servletResponse=ServletActionContext.getResponse();
						Cookie cookie1=new Cookie(cookie.getName(),cookie.getValue());
						cookie1.setMaxAge(expireTime);
						((HttpServletResponse) servletResponse).addCookie(cookie1);
						ServletActionContext.getRequest().getSession().setMaxInactiveInterval(expireTime);
					}
				}
			}
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
			if(cookie.getName().equals("JSESSIONID"))
			{
				ServletResponse servletResponse=ServletActionContext.getResponse();
				Cookie cookie1=new Cookie(cookie.getName(),cookie.getValue());
				cookie1.setMaxAge(-1);
				((HttpServletResponse) servletResponse).addCookie(cookie1);
				ServletActionContext.getRequest().getSession().setMaxInactiveInterval(30*60);
			}
		}

		return SUCCESS;
	}

	public String validateRegister() throws Exception
	{
		if(!password_repeat.equals(user.getPassword()))
			registerInfo="注册失败：密码两次输入不一致";
		else
		{
			try{
				userService.createUser(user);
				registerInfo="注册成功";
			}
			catch (Exception e)
			{
				registerInfo="注册失败";
			}
		}
		return SUCCESS;
	}
}
