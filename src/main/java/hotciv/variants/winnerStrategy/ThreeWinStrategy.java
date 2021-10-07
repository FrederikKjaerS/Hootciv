package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.framework.Player;

import java.util.HashMap;
import java.util.Map;

public class ThreeWinStrategy implements WinnerStrategy {
    private Map<Player, Integer> wins;

    public ThreeWinStrategy() {
        this.wins = new HashMap<>();
        wins.put(Player.RED, 0);
        wins.put(Player.BLUE, 0);
    }

    public int getWins(Player p){
        //wins.get(p);
        return 0;
    }

    public void incrementWin(Player p){
        wins.put(p, wins.get(p) + 1);
    }

    @Override
    public Player getWinner(ExtendedGame game) {
        if(wins.get(Player.RED) >= 3){
            return Player.RED;
        }
        if(wins.get(Player.BLUE) >= 3){
            return Player.BLUE;
        }
        return null;
    }
}
