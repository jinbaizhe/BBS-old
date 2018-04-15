package aop;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import service.FollowpostService;
import service.PostService;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UpdateAspect extends PostAndFollowpostControl {

    public String Around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        User loginUser=getLoginUser();
        User author=getAuthor();
        if(loginUser.getId()==author.getId())
        {
            return (String)joinPoint.proceed();
        }
        else
        {
            saveReferURL();
            return "reject";
        }
    }
}
