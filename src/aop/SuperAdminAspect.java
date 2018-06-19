package aop;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Component("superAdminAspect")
@Aspect
public class SuperAdminAspect {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Around("execution(* action.manage.*.*NeedSuperAdmin*(..))")
    public String around(ProceedingJoinPoint jp) throws Throwable
    {
        ActionContext context=ActionContext.getContext();
        Map session=context.getSession();
        User user=userService.getUserByid(((User)session.get("user")).getId());
        String s=null;
        if(user.getType()==2)
        {
            s=(String) jp.proceed();
            return s;
        }
        else
        {
            HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            session.put("referURL",request.getHeader("referer"));
            return "reject";
        }
    }
}
