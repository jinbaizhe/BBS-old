package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Picture {
    private int id;
    private String picture;
    private Timestamp uploadTime;
    private Set<FollowpostPicture> followpostPictures;
    private Set<PostPicture> postPictures;
    private Set<User> users;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 30)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "upload_time", nullable = true)
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture1 = (Picture) o;
        return id == picture1.id &&
                Objects.equals(picture, picture1.picture) &&
                Objects.equals(uploadTime, picture1.uploadTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, picture, uploadTime);
    }

    @OneToMany(mappedBy = "picture")
    public Set<FollowpostPicture> getFollowpostPictures() {
        return followpostPictures;
    }

    public void setFollowpostPictures(Set<FollowpostPicture> followpostPictures) {
        this.followpostPictures = followpostPictures;
    }

    @OneToMany(mappedBy = "picture")
    public Set<PostPicture> getPostPictures() {
        return postPictures;
    }

    public void setPostPictures(Set<PostPicture> postPictures) {
        this.postPictures = postPictures;
    }

    @OneToMany(mappedBy = "pictureByPictureId")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
