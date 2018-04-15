package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.UserService;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginAction extends ActionSupport {
	private UserService userService;
	private User user;
	private String registerInfo;
	private String loginInfo;
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
		try{
			userService.createUser(user);
		}
		catch (Exception e)
		{
			registerInfo="注册失败";
			return "error";
		}
		registerInfo="注册成功";
		return SUCCESS;
	}
}
