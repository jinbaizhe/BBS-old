package aop;


import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginAspect {

    public String around(ProceedingJoinPoint jp) throws Throwable
    {
        ActionContext context=ActionContext.getContext();
        Map session=context.getSession();
        User user=(User)session.get("user");
        String s=null;
        if (user!=null) {
            s=(String) jp.proceed();
        }
        else
        {
            s="notlogin";
            HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            session.put("referURL",request.getHeader("referer"));
        }
        return s;
    }
}
