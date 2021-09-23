package hotciv.variants.winnerStrategy;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ConquerAllWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(GameImpl game) {
        int citiesThatRedOwns = 0;
        int citiesThatBlueOwns = 0;
        for(City c:game.getCities().values()) {
            if(c.getOwner() == Player.RED){
                citiesThatRedOwns++;
            }
            if(c.getOwner() == Player.BLUE){
                citiesThatBlueOwns++;
            }
        }
        if(citiesThatRedOwns == game.getCities().size()) {
            return Player.RED;
        }
        if(citiesThatBlueOwns == game.getCities().size()) {
            return Player.BLUE;
        }
        return null;
    }
}
