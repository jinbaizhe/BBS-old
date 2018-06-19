package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import vo.User;
@Component("deleteAspect")
@Aspect
public class DeleteAspect extends PostAndFollowpostControl {

    @Around("execution(* action.*.*Delete*(..))")
    public String Around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        User loginUser=getLoginUser();
        User author=getAuthor();
        if(loginUser.getId()==author.getId()||loginUser.getType()==1)
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
