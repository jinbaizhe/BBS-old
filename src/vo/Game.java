package vo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Game {
    private int id;
    private String home;
    private String away;
    private Date date;
    private Set<GameLink> gameLinks;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "home")
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Basic
    @Column(name = "away")
    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                Objects.equals(home, game.home) &&
                Objects.equals(away, game.away) &&
                Objects.equals(date, game.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, home, away, date);
    }

    @OneToMany(mappedBy = "game")
    public Set<GameLink> getGameLinks() {
        return gameLinks;
    }

    public void setGameLinks(Set<GameLink> gameLinks) {
        this.gameLinks = gameLinks;
    }
}
