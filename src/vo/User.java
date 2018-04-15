package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    private int id;
    private String username;
    private String password;
    private String info;
    private String sex;
    private String email;
    private Integer status;
    private Integer type;
    private Integer level;
    private Timestamp registerTime;
    private Set<Followpost> followposts;
    private Set<Post> posts;
    private Picture pictureByPictureId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 50)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 4)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "register_time", nullable = true)
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(info, user.info) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(email, user.email) &&
                Objects.equals(status, user.status) &&
                Objects.equals(type, user.type) &&
                Objects.equals(level, user.level) &&
                Objects.equals(registerTime, user.registerTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, info, sex, email, status, type, level, registerTime);
    }

    @OneToMany(mappedBy = "user")
    public Set<Followpost> getFollowposts() {
        return followposts;
    }

    public void setFollowposts(Set<Followpost> followposts) {
        this.followposts = followposts;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @ManyToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    public Picture getPictureByPictureId() {
        return pictureByPictureId;
    }

    public void setPictureByPictureId(Picture pictureByPictureId) {
        this.pictureByPictureId = pictureByPictureId;
    }
}
