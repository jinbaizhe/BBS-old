package service;

import dao.PictureDAO;
import vo.Followpost;
import vo.Picture;
import vo.Post;

import java.util.List;

public interface PictureService {
    public void deletePictureFromPost(Picture picture, Post post);
    public void deletePictureFromFollowpost(Picture picture, Followpost followpost);
    public Picture getPictureById(int id);
    public List getPicturesByPostId(int id);
    public List getPicturesByFollowpostId(int id);
}
