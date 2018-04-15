package vo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "game_link", schema = "bbs", catalog = "")
public class GameLink {
    private int id;
    private String info;
    private Integer orderNum;
    private Game game;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "order_num")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameLink gameLink = (GameLink) o;
        return id == gameLink.id &&
                Objects.equals(info, gameLink.info) &&
                Objects.equals(orderNum, gameLink.orderNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, info, orderNum);
    }

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
