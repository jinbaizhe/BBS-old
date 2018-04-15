package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Post {
    private int id;
    private String title;
    private String content;
    private Timestamp sendTime;
    private Timestamp updateTime;
    private Integer type;
    private Integer top;
    private Integer viewNum;
    private Set<Followpost> followposts;
    private SubForum subForum;
    private User user;
    private Set<PostPicture> postPictures;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "top", nullable = true)
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Basic
    @Column(name = "view_num", nullable = true)
    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(sendTime, post.sendTime) &&
                Objects.equals(updateTime, post.updateTime) &&
                Objects.equals(type, post.type) &&
                Objects.equals(top, post.top) &&
                Objects.equals(viewNum, post.viewNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, sendTime, updateTime, type, top, viewNum);
    }

    @OneToMany(mappedBy = "post")
    public Set<Followpost> getFollowposts() {
        return followposts;
    }

    public void setFollowposts(Set<Followpost> followposts) {
        this.followposts = followposts;
    }

    @ManyToOne
    @JoinColumn(name = "sub_forum_id", referencedColumnName = "id")
    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "post")
    public Set<PostPicture> getPostPictures() {
        return postPictures;
    }

    public void setPostPictures(Set<PostPicture> postPictures) {
        this.postPictures = postPictures;
    }
}
