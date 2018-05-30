package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.FollowpostService;
import service.PictureService;
import service.PostService;
import vo.Followpost;
import vo.User;

public class FollowpostAction extends ActionSupport {
    private Followpost followpost;
    private FollowpostService followpostService;
    private PostService postService;
    private int postid;
    private int followpostid;

    public Followpost getFollowpost() {
        return followpost;
    }

    public void setFollowpost(Followpost followpost) {
        this.followpost = followpost;
    }

    public FollowpostService getFollowpostService() {
        return followpostService;
    }

    public void setFollowpostService(FollowpostService followpostService) {
        this.followpostService = followpostService;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getFollowpostid() {
        return followpostid;
    }

    public void setFollowpostid(int followpostid) {
        this.followpostid = followpostid;
    }

    public String commitAddFollowpostNeedUserLogin() throws Exception
    {
        followpost.setPost(postService.getPostById(postid));
        followpost.setUser((User)ActionContext.getContext().getSession().get("user"));
        followpostService.createFollowpost(followpost);
        return SUCCESS;
    }

    public String getUpdateFollowpostPageNeedUserLogin()
    {
        followpost=followpostService.getFollowpostById(followpostid);
        return SUCCESS;
    }

    public String commitUpdateFollowpostNeedUserLogin()
    {
        followpostService.updateFollowpost(followpost);
        followpost=followpostService.getFollowpostById(followpost.getId());
        return SUCCESS;
    }

    public String commitDeleteFollowpostNeedUserLogin()
    {
        followpost=followpostService.getFollowpostById(followpostid);
        followpostService.deleteFollowpost(followpost);
        return SUCCESS;
    }
}
