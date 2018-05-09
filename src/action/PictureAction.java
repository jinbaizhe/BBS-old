package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import service.PictureService;
import util.VerifyCode;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.util.Map;

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


    public String getVerifyCode()
    {
        filename="VerifyCode.jpg";
        return SUCCESS;
    }
    public InputStream getVerifyCodeInputStream()
    {
        Map session=ActionContext.getContext().getSession();
        VerifyCode verify = (VerifyCode)session.get("verify");
        if(verify==null)
        {
            verify=new VerifyCode(100,30,5);
            session.put("verify",verify);
        }
        verify.change();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            ImageOutputStream imageOutputStream=ImageIO.createImageOutputStream(bs);
            ImageIO.write(verify.getImage(),"jpg",imageOutputStream);
            is=new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}
