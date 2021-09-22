package hotciv.variants.betaCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;

public class ConquerAllWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(GameImpl game) {
        return Player.RED;
    }
}
