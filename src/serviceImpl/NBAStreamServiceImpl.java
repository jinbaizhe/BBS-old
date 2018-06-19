package serviceImpl;

import dao.GameDAO;
import dao.GameLinkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.NBAStreamService;
import vo.Game;
import java.util.List;

@Component("nbaStreamService")
public class NBAStreamServiceImpl implements NBAStreamService {
    private GameDAO gameDAO;
    private GameLinkDAO gameLinkDAO;

    public GameDAO getGameDAO() {
        return gameDAO;
    }

    @Autowired
    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public GameLinkDAO getGameLinkDAO() {
        return gameLinkDAO;
    }

    @Autowired
    public void setGameLinkDAO(GameLinkDAO gameLinkDAO) {
        this.gameLinkDAO = gameLinkDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllGames() {
        return gameDAO.getAllGames();
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllGameLinksByGameId(int id) {
        return gameLinkDAO.getAllGameLinksByGameId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Game getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List getOldGames(int num) {
        return gameDAO.getOldGames(num);
    }
}
