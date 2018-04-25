package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.PostService;
import service.SubForumService;
import util.Pager;
import vo.SubForum;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;


public class SubForumAction extends ActionSupport {

    private PostService postService;
    private SubForumService subForumService;
    private int page=1;
    private int sfid;
    private SubForum subForum;
    private List posts;
    private int totalPostsNum;
    private String order="lastfollowpost";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public int getSfid() {
        return sfid;
    }

    public void setSfid(int sfid) {
        this.sfid = sfid;
    }

    public SubForumService getSubForumService() {
        return subForumService;
    }

    public void setSubForumService(SubForumService subForumService) {
        this.subForumService = subForumService;
    }

    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }

    public int getTotalPostsNum() {
        return totalPostsNum;
    }

    public void setTotalPostsNum(int totalPostsNum) {
        this.totalPostsNum = totalPostsNum;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String browserSubForum()
    {
        if(sfid==0)
            return ERROR;
        subForum=subForumService.getSubForumById(sfid);
        totalPostsNum=postService.getPostsNumBySubForumId(sfid);

        //读取web.xml获取ShowFollowpostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowPostsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowPostsPerPageNum"));

        posts=postService.getPostsBySubForumId(sfid,page, ShowPostsPerPageNum,order);
        Map request=(Map) ActionContext.getContext().get("request");
        Pager pager=new Pager(page,ShowPostsPerPageNum,totalPostsNum);
        request.put("pager",pager);
        return SUCCESS;
    }
}
