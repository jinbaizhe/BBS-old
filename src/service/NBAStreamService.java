package service;

import vo.Game;
import vo.GameLink;

import java.util.List;

public interface NBAStreamService {
    public List getAllGames();
    public List getAllGameLinksByGameId(int id);
    public Game getGameById(int id);
    public List getOldGames(int num);
}
