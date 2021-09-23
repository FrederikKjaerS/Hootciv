package hotciv.variants;

import hotciv.framework.Player;
import hotciv.standard.*;

public interface WinnerStrategy {
    Player getWinner(GameImpl game);
}
