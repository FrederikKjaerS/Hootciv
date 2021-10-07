package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

public interface WinnerStrategy {
    Player getWinner(ExtendedGame game);

    void incrementWin(Player p);
}
