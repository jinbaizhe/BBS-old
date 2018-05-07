package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.FollowpostService;
import service.PictureService;
import service.PostService;
import service.SubForumService;
import util.Pager;
import vo.*;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;
import java.util.Map;


public class PostAction extends ActionSupport {
    private int postid;
    private PostService postService;
    private SubForumService subForumService;
    private FollowpostService followpostService;
    private PictureService pictureService;
    private Post post;
    private int subforumid;
    private SubForum subForum;
    private List followposts;
    private List pictures;
    private int page=1;
    private List<File> files;
    private String order="asc";


    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    public SubForumService getSubForumService() {
        return subForumService;
    }

    public void setSubForumService(SubForumService subForumService) {
        this.subForumService = subForumService;
    }

    public FollowpostService getFollowpostService() {
        return followpostService;
    }

    public void setFollowpostService(FollowpostService followpostService) {
        this.followpostService = followpostService;
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public List getFollowposts() {
        return followposts;
    }

    public void setFollowposts(List followposts) {
        this.followposts = followposts;
    }

    public List getPictures() {
        return pictures;
    }

    public void setPictures(List pictures) {
        this.pictures = pictures;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getSubforumid() {
        return subforumid;
    }

    public void setSubforumid(int subforumid) {
        this.subforumid = subforumid;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String browserPost() throws Exception {
        if(postid==0)
            return ERROR;
        post=postService.getPostById(postid);
        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);

        //读取web.xml获取ShowFollowpostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowFollowpostsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowFollowpostsPerPageNum"));

        followposts=followpostService.getFollowpostsByPostId(postid,page, ShowFollowpostsPerPageNum,order);
        pictures=pictureService.getPicturesByPostId(post.getId());
        Map request=(Map)ActionContext.getContext().get("request");

        //读取web.xml获取ShowPostsPerPageNum参数
        final int ShowPostsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowPostsPerPageNum"));

        Pager pager=new Pager(page,ShowFollowpostsPerPageNum,totalFollowpostsNum);
        request.put("pager",pager);
        return SUCCESS;
    }

    public String getAddPostPageNeedUserLogin() throws Exception
    {
        if(subforumid==0)
            return ERROR;
        subForum=subForumService.getSubForumById(subforumid);
        return SUCCESS;
    }

    public String commitAddPostNeedUserLogin() throws Exception
    {
        if(post==null)
            return ERROR;
        post.setSubForum(subForumService.getSubForumById(subforumid));
        Map session=ActionContext.getContext().getSession();
        post.setUser((User)session.get("user"));

        postService.createPost(post,files);

        return SUCCESS;
    }

    public String getUpdatePostPageNeedUserLogin() throws Exception
    {
        post=postService.getPostById(postid);
        return SUCCESS;
    }

    public String commitUpdatePostNeedUserLogin() throws Exception
    {
        postService.updatePost(post);
        post=postService.getPostById(post.getId());
        return SUCCESS;
    }

    public String commitDeletePostNeedUserLogin() throws Exception
    {
        post=postService.getPostById(postid);
        postService.deletePost(post);
        return SUCCESS;
    }
}
