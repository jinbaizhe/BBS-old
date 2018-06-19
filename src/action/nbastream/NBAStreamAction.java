package action.nbastream;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.NBAStreamService;
import vo.Game;

import java.util.List;
@Controller("nbaStreamAction")
@Scope("prototype")
public class NBAStreamAction extends ActionSupport {
    private List games;
    private List gameLinks;
    private List old_games;
    private int page;
    private int gameid;
    private Game game;
    @Autowired
    private NBAStreamService nbaStreamService;

    public NBAStreamService getNbaStreamService() {
        return nbaStreamService;
    }

    public void setNbaStreamService(NBAStreamService nbaStreamService) {
        this.nbaStreamService = nbaStreamService;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public List getGames() {
        return games;
    }

    public void setGames(List games) {
        this.games = games;
    }

    public List getGameLinks() {
        return gameLinks;
    }

    public void setGameLinks(List gameLinks) {
        this.gameLinks = gameLinks;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List getOld_games() {
        return old_games;
    }

    public void setOld_games(List old_games) {
        this.old_games = old_games;
    }

    public String getAllGamesPage()
    {
        games=nbaStreamService.getAllGames();
        old_games=nbaStreamService.getOldGames(5);
        return SUCCESS;
    }

    public String getAllGameLinksByGameIdPage()
    {
        gameLinks=nbaStreamService.getAllGameLinksByGameId(gameid);
        game=nbaStreamService.getGameById(gameid);
        return SUCCESS;
    }
}
