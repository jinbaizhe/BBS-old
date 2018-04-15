package advice;

import com.opensymphony.xwork2.ActionContext;
import org.springframework.aop.MethodBeforeAdvice;
import vo.User;

import java.lang.reflect.Method;
import java.util.Map;

public class ValidateIsLoginAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        Map session= ActionContext.getContext().getSession();
        User user=(User)session.get("user");
        if (user==null)
            throw new NullPointerException("用户未登录");
    }
}
