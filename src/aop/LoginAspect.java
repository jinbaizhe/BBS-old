package aop;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;
import util.AESEncrypt;
import vo.User;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("loginAspect")
@Aspect
public class LoginAspect {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Around("execution(* action.*.*NeedUserLogin*(..)) || execution(* action.*.*.*NeedUserLogin*(..))")
    public String around(ProceedingJoinPoint jp) throws Throwable
    {
        //读取web.xml获取login_expire_time参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int expireTime=Integer.valueOf(servletContext.getInitParameter("login_expire_time"));

        User user=null;
        Cookie[] cookies =ServletActionContext.getRequest().getCookies();
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals("userid")&&cookie.getValue()!=null)
            {
                int userid=Integer.valueOf(AESEncrypt.decrypt(cookie.getValue()));
                user=userService.getUserByid(userid);
                HttpServletResponse servletResponse=ServletActionContext.getResponse();
                Cookie cookie1=new Cookie(cookie.getName(),cookie.getValue());
                cookie1.setPath(cookie.getPath());
                cookie1.setMaxAge(expireTime);//延长cookie的过期时间
                servletResponse.addCookie(cookie1);
                break;
            }
        }
        String s=null;
        if (user!=null) {
            s=(String) jp.proceed();
        }
        else
        {
            s="notlogin";
            ActionContext context=ActionContext.getContext();
            Map session=context.getSession();
            HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            session.put("referURL",request.getHeader("referer"));
        }
        return s;
    }
}
