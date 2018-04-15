package action.nbastream;

import com.opensymphony.xwork2.ActionSupport;
import service.NBAStreamService;
import vo.Game;

import java.util.List;

public class NBAGameAction extends ActionSupport {
    private List games;
    private List gameLinks;
    private int page;
    private int gameid;
    private Game game;
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

    public String getAllGamesPage()
    {
        games=nbaStreamService.getAllGames();
        return SUCCESS;
    }

    public String getAllGameLinksByGameIdPage()
    {
        gameLinks=nbaStreamService.getAllGameLinksByGameId(gameid);
        game=nbaStreamService.getGameById(gameid);
        return SUCCESS;
    }
}
