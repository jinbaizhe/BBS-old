package serviceImpl;

import dao.GameDAO;
import dao.GameLinkDAO;
import service.NBAStreamService;
import vo.Game;

import java.util.List;

public class NBAStreamServiceImpl implements NBAStreamService {
    private GameDAO gameDAO;
    private GameLinkDAO gameLinkDAO;

    public GameDAO getGameDAO() {
        return gameDAO;
    }

    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public GameLinkDAO getGameLinkDAO() {
        return gameLinkDAO;
    }

    public void setGameLinkDAO(GameLinkDAO gameLinkDAO) {
        this.gameLinkDAO = gameLinkDAO;
    }

    @Override
    public List getAllGames() {
        return gameDAO.getAllGames();
    }

    @Override
    public List getAllGameLinksByGameId(int id) {
        return gameLinkDAO.getAllGameLinksByGameId(id);
    }

    @Override
    public Game getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    @Override
    public List getOldGames(int num) {
        return gameDAO.getOldGames(num);
    }
}
