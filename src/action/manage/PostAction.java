package action.manage;

import com.opensymphony.xwork2.ActionSupport;
import service.PostService;
import vo.Post;

public class PostAction extends ActionSupport {
    private PostService postService;
    private int postid;

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

    public String setPostTopNeedUserLoginNeedManageLogin()
    {
        Post post=postService.getPostById(postid);
        post.setTop(1);
        postService.updatePostAllAttr(post);
        return SUCCESS;
    }

    public String unsetPostTopNeedUserLoginNeedManageLogin()
    {
        Post post=postService.getPostById(postid);
        post.setTop(0);
        postService.updatePostAllAttr(post);
        return SUCCESS;
    }
    public String setPostEssentialNeedUserLoginNeedManageLogin()
    {
        Post post=postService.getPostById(postid);
        post.setType(1);
        postService.updatePostAllAttr(post);
        return SUCCESS;
    }

    public String unsetPostEssentialNeedUserLoginNeedManageLogin()
    {
        Post post=postService.getPostById(postid);
        post.setType(0);
        postService.updatePostAllAttr(post);
        return SUCCESS;
    }
}
