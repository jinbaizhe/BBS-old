package serviceImpl;

import dao.PictureDAO;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PictureService;
import vo.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

@Component("pictureService")
public class PictureServiceImpl implements PictureService {
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    @Autowired
    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deletePicture(Picture picture)
    {
        pictureDAO.deletePicture(picture);
    }

    @Override
    @Transactional(readOnly = true)
    public Picture getPictureById(int id) {
        return pictureDAO.getPictureById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Picture uploadPicture(File file,String uploadFileName,String uploadContentType) {
        String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload/");
        Calendar calendar=Calendar.getInstance();
        if(file!=null)
        {
            File dest_file=new File(path+calendar.getTimeInMillis()+file.hashCode()+".jpg");
            try {
                Files.copy(file.toPath(),dest_file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.delete();
            Picture picture=new Picture();
            picture.setPicture(dest_file.getName());
            pictureDAO.createPicture(picture);
            return picture;
        }
        return null;
    }
}
