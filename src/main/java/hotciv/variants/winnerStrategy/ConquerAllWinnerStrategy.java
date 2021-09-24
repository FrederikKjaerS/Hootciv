package hotciv.variants.winnerStrategy;

import hotciv.framework.City;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

public class ConquerAllWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(ExtendedGame game) {
        int citiesThatRedOwns = 0;
        for (City c : game.getCities().values()) {
            if (c.getOwner() == Player.RED) {
                citiesThatRedOwns++;
            }
            if (citiesThatRedOwns == game.getCities().size()) {
                return Player.RED;
            } else if (citiesThatRedOwns == 0) {
                return Player.BLUE;
            }
        }
        return null;
    }
}