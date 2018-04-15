package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Followpost {
    private int id;
    private String content;
    private Timestamp sendTime;
    private Timestamp updateTime;
    private Post post;
    private User user;
    private Set<FollowpostPicture> followpostPictures;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "send_time", nullable = true)
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Followpost that = (Followpost) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, sendTime, updateTime);
    }

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "followpost")
    public Set<FollowpostPicture> getFollowpostPictures() {
        return followpostPictures;
    }

    public void setFollowpostPictures(Set<FollowpostPicture> followpostPictures) {
        this.followpostPictures = followpostPictures;
    }
}
