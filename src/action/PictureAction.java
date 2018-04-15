package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.PictureService;

import java.io.InputStream;

public class PictureAction extends ActionSupport{
    private PictureService pictureService;
    private int id;
    private String filename;
    public PictureService getPictureService() {
        return pictureService;
    }

    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPictureById()
    {
        filename=pictureService.getPictureById(id).getPicture();
        return SUCCESS;
    }
    public InputStream getAttrInputStream()
    {
        String path="/WEB-INF/upload/";
        return ServletActionContext.getServletContext().getResourceAsStream(path+filename);
    }
}
