package vo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "followpost_picture", schema = "bbs", catalog = "")
public class FollowpostPicture {
    private int id;
    private Followpost followpost;
    private Picture picture;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowpostPicture that = (FollowpostPicture) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "followpost_id", referencedColumnName = "id")
    public Followpost getFollowpost() {
        return followpost;
    }

    public void setFollowpost(Followpost followpost) {
        this.followpost = followpost;
    }

    @ManyToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
