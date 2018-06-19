package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.PictureService;
import service.UserService;
import vo.Picture;
import vo.User;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller("uploadFileAction")
@Scope("prototype")
public class UploadFileAction extends ActionSupport {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    private File upload;  //文件
    private String uploadContentType;  //文件类型
    private String uploadFileName;   //文件名
    private Map jsonData;


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public Map getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map jsonData) {
        this.jsonData = jsonData;
    }

    public String uploadImage()
    {
        //暂未要求登录后才能上传图片！
        Picture picture=pictureService.uploadPicture(upload,uploadFileName,uploadContentType);
        jsonData = new HashMap<String,Object>();
        jsonData.put("uploaded", 1);
        jsonData.put("fileName", getUploadFileName());
        jsonData.put("url", "getPicture.action?id="+picture.getId());
        return SUCCESS;
    }
    private User getSessionUser()
    {
        Map session= ActionContext.getContext().getSession();
        return (User)session.get("user");
    }
    public String uploadAvatar()
    {
        if(upload!=null)
        {
            //暂未要求登录后才能上传头像！
            Picture picture=pictureService.uploadPicture(upload,uploadFileName,uploadContentType);
            User temp_user=getSessionUser();
            temp_user.setPicture(picture);
            userService.updateUser(temp_user);
        }
        return SUCCESS;
    }
}
