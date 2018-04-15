package vo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "main_forum", schema = "bbs", catalog = "")
public class MainForum {
    private int id;
    private String name;
    private String info;
    private Timestamp createTime;
    private Set<SubForum> subForums;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "create_time", nullable = true)
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
        MainForum mainForum = (MainForum) o;
        return id == mainForum.id &&
                Objects.equals(name, mainForum.name) &&
                Objects.equals(info, mainForum.info) &&
                Objects.equals(createTime, mainForum.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, info, createTime);
    }

    @OneToMany(mappedBy = "mainForum")
    public Set<SubForum> getSubForums() {
        return subForums;
    }

    public void setSubForums(Set<SubForum> subForums) {
        this.subForums = subForums;
    }
}
