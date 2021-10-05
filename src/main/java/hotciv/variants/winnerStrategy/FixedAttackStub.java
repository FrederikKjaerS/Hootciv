package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.framework.Player;

public class FixedAttackStub implements AttackDecisionWinnerStrategy {
    private int redWinnerNmb = 0;

    public FixedAttackStub(int nmbOfAttacks){
        redWinnerNmb = nmbOfAttacks;
    }

    @Override
    public int getPlayersSuccessfulAttacks(ExtendedGame game, Player p) {
        return redWinnerNmb;
    }
}

