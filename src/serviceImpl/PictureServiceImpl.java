package serviceImpl;

import dao.PictureDAO;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import service.PictureService;
import vo.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;

public class PictureServiceImpl implements PictureService {
    private PictureDAO pictureDAO;

    public PictureDAO getPictureDAO() {
        return pictureDAO;
    }

    public void setPictureDAO(PictureDAO pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    public void deletePicture(Picture picture)
    {
        pictureDAO.deletePicture(picture);
    }

    @Override
    public Picture getPictureById(int id) {
        return pictureDAO.getPictureById(id);
    }

    @Override
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

            Session session = pictureDAO.getSession();
            session.beginTransaction();//事务开始
            pictureDAO.createPicture(picture);
            session.flush();
            session.getTransaction().commit();//事务提交
            return picture;
        }
        return null;
    }
}
