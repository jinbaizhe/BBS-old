package aop;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import service.FollowpostService;
import service.PostService;
import vo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class PostAndFollowpostControl {
    @Autowired
    private PostService postService;
    @Autowired
    private FollowpostService followpostService;
    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public FollowpostService getFollowpostService() {
        return followpostService;
    }

    public void setFollowpostService(FollowpostService followpostService) {
        this.followpostService = followpostService;
    }
    public User getLoginUser()
    {
        ActionContext context=ActionContext.getContext();
        Map session=context.getSession();
        User loginUser=(User)session.get("user");
        return loginUser;
    }
    public User getAuthor()
    {
        ActionContext context=ActionContext.getContext();
        HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        String tempid=null;
        if (request.getParameter("postid")!=null)
        {
            tempid=request.getParameter("postid");
            return postService.getPostById(Integer.valueOf(tempid)).getUser();
        }
        else if (request.getParameter("followpostid")!=null)
        {
            tempid=request.getParameter("followpostid");
            return followpostService.getFollowpostById(Integer.valueOf(tempid)).getUser();
        }
        return null;
    }

    public void saveReferURL()
    {
        ActionContext context=ActionContext.getContext();
        Map session=context.getSession();
        HttpServletRequest request=(HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        session.put("referURL",request.getHeader("referer"));
    }
}
