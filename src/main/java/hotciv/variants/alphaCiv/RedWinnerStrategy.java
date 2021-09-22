package hotciv.variants.alphaCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.*;

public class RedWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(GameImpl game) {
        if (game.getAge() == -3000) {
            return Player.RED;
        }
        return null;
    }
}
