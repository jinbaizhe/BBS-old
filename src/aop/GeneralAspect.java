package aop;

import com.opensymphony.xwork2.ActionContext;
import dao.UserDAO;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.AESEncrypt;
import vo.User;

import javax.servlet.http.Cookie;
import java.util.Map;

@Component("generalAspect")
@Aspect
public class GeneralAspect {
    @Autowired
    private UserDAO userDAO;
    @Before("execution(* action.*.*(..))")
    public void before()
    {
        Map session=ActionContext.getContext().getSession();
        User user=(User) session.get("user");
        Cookie cookie=null;
        Cookie[] cookies=ServletActionContext.getRequest().getCookies();
        if(cookies!=null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userid") && c.getValue() != null) {
                    cookie = c;
                    break;
                }
            }
            if(user==null&&cookie!=null&&cookie.getValue()!=null)
            {
                session.put("user",userDAO.getUser(Integer.valueOf(AESEncrypt.decrypt(cookie.getValue()))));
            }
        }
    }
}
