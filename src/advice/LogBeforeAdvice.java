package advice;

import com.opensymphony.xwork2.ActionContext;
import com.sun.istack.internal.logging.Logger;
import org.springframework.aop.MethodBeforeAdvice;
import vo.User;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;

public class LogBeforeAdvice implements MethodBeforeAdvice {
    private Logger logger= Logger.getLogger(this.getClass().getName(),this.getClass());
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        Map session=ActionContext.getContext().getSession();
        User user=(User)session.get("user");
        if (user==null)
            throw new NullPointerException("没有权限");
        logger.log(Level.INFO,"方法执行前");
    }


}
