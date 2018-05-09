package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sub_forum", schema = "bbs", catalog = "")
public class SubForum {
    private int id;
    private String name;
    private String info;
    private Timestamp createTime;
    private Set<Post> posts;
    private MainForum mainForum;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubForum subForum = (SubForum) o;
        return id == subForum.id &&
                Objects.equals(name, subForum.name) &&
                Objects.equals(info, subForum.info) &&
                Objects.equals(createTime, subForum.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, info, createTime);
    }

    @OneToMany(mappedBy = "subForum")
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @ManyToOne
    @JoinColumn(name = "main_forum_id", referencedColumnName = "id")
    public MainForum getMainForum() {
        return mainForum;
    }

    public void setMainForum(MainForum mainForum) {
        this.mainForum = mainForum;
    }
}
