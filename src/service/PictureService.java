package service;

import dao.PictureDAO;
import vo.Followpost;
import vo.Picture;
import vo.Post;

import java.io.File;
import java.util.List;

public interface PictureService {
    public Picture getPictureById(int id);
    public Picture uploadPicture(File file,String uploadFileName,String uploadContentType);
}
