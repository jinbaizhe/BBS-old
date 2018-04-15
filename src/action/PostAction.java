package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.FollowpostService;
import service.PictureService;
import service.PostService;
import service.SubForumService;
import util.Num;
import util.Pager;
import vo.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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

    public String browserPost() throws Exception {
        if(postid==0)
            return ERROR;
        post=postService.getPostById(postid);
        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);
        followposts=followpostService.getFollowpostsByPostId(postid,page, Num.ShowFollowpostsPerPageNum.getValue());
        pictures=pictureService.getPicturesByPostId(post.getId());
        Map request=(Map)ActionContext.getContext().get("request");
        Pager pager=new Pager(page,Num.ShowFollowpostsPerPageNum.getValue(),totalFollowpostsNum);
        request.put("pager",pager);
        return SUCCESS;
    }

    public String getAddPostPage() throws Exception
    {
        if(subforumid==0)
            return ERROR;
        subForum=subForumService.getSubForumById(subforumid);
        return SUCCESS;
    }

    public String commitAddPost() throws Exception
    {
        if(post==null)
            return ERROR;
        post.setSubForum(subForumService.getSubForumById(subforumid));
        Map session=ActionContext.getContext().getSession();
        post.setUser((User)session.get("user"));
        postService.createPost(post);
        String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");
        if(files!=null)
        {
            for(File source_file:files)
            {
                File dest_file=new File(path+post.hashCode()+""+source_file.hashCode()+".jpg");
                Files.copy(source_file.toPath(),dest_file.toPath());
                source_file.delete();
                Picture picture=new Picture();
                picture.setPicture(dest_file.getName());
                pictureService.createPictureFromPost(picture,post);
            }
        }
        return SUCCESS;
    }

    public String getUpdatePostPage() throws Exception
    {
        post=postService.getPostById(postid);
        return SUCCESS;
    }

    public String commitUpdatePost() throws Exception
    {
        postService.updatePost(post);
        post=postService.getPostById(post.getId());
        return SUCCESS;
    }

    public String commitDeletePost() throws Exception
    {
        post=postService.getPostById(postid);
        postService.deletePost(post);
        return SUCCESS;
    }
}
