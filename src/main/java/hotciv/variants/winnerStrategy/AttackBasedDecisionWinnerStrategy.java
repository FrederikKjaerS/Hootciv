package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.framework.Player;

public class AttackBasedDecisionWinnerStrategy implements AttackDecisionWinnerStrategy{

    @Override
    public int getPlayersSuccessfulAttacks(ExtendedGame game, Player p) {
        return 0;
    }
}
