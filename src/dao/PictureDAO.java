package dao;

import vo.Picture;

import java.util.List;

public interface PictureDAO {
    public void createPicture(Picture picture);
    public void deletePicture(Picture picture);
    public Picture getPictureById(int id);
    public List getPicturesByPostId(int id);
    public List getPicturesByFollowpostId(int id);
}
