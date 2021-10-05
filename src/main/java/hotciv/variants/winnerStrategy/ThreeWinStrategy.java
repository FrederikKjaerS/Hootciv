package hotciv.variants.winnerStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

public class ThreeWinStrategy implements WinnerStrategy {
    private AttackDecisionWinnerStrategy attackDecisionWinnerStrategy;

    public ThreeWinStrategy(AttackDecisionWinnerStrategy attackDecisionWinnerStrategy){
        this.attackDecisionWinnerStrategy = attackDecisionWinnerStrategy;
    }

    @Override
    public Player getWinner(ExtendedGame game) {
        return null;
    }
}
