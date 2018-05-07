package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import vo.User;

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
		if(user!=null)
		{
			Map session= ActionContext.getContext().getSession();
			session.put("user",user);
			if(autoLogin!=null&&autoLogin.equals("true"))
			{
				//当用户勾选自动登录时
				//暂未实现
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
