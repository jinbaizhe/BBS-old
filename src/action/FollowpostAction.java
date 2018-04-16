package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.FollowpostService;
import service.PictureService;
import service.PostService;
import vo.Followpost;
import vo.Picture;
import vo.User;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class FollowpostAction extends ActionSupport {
    private Followpost followpost;
    private FollowpostService followpostService;
    private PostService postService;
    private int postid;
    private int followpostid;
    private List<File> files;
    private PictureService pictureService;

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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public String commitAddFollowpostNeedUserLogin() throws Exception
    {
        followpost.setPost(postService.getPostById(postid));
        followpost.setUser((User)ActionContext.getContext().getSession().get("user"));
        followpostService.createFollowpost(followpost);

        String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");
        if(files!=null) {
            for (File source_file : files) {
                File dest_file = new File(path + followpost.hashCode() + "" + source_file.hashCode() + ".jpg");
                Files.copy(source_file.toPath(), dest_file.toPath());
                source_file.delete();
                Picture picture = new Picture();
                picture.setPicture(dest_file.getName());
                pictureService.createPictureFromFollowpost(picture, followpost);
            }
        }
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
