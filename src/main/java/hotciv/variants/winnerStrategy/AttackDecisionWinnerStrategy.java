package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.framework.Player;

public interface AttackDecisionWinnerStrategy {
    int getPlayersSuccessfulAttacks(ExtendedGame game, Player p);
}
