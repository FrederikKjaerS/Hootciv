package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

public class RedWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(ExtendedGame game) {
        if (game.getAge() == -3000) {
            return Player.RED;
        }
        return null;
    }

    @Override
    public void incrementWin(ExtendedGame game, Player p) {
    }
}
