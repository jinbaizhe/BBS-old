package dao;

import vo.Game;

import java.util.List;

public interface GameDAO {
    public List getAllGames();
    public Game getGameById(int id);
}
