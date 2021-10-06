package hotciv.variants.attackStrategy;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.utility.Utility2;
import hotciv.variants.attackStrategy.dieDecisionStrategy.DieDecisionStrategy;

public class AlgoAttackingStrategy implements AttackStrategy {
    private DieDecisionStrategy fromsDie;
    private DieDecisionStrategy tosDie;
    private int fromAttack;
    private int toDefense;

    public AlgoAttackingStrategy(DieDecisionStrategy dieDecisionStrategy) {
        this.fromsDie = dieDecisionStrategy;
        this.tosDie = dieDecisionStrategy;
    }

    @Override
    public boolean unitWins(ExtendedGame game, Position from, Position to) {
        updateStats(game,from,to);
        if( fromAttack * fromsDie.getEyes() > toDefense * tosDie.getEyes()) {
            return true;
        }
        return false;
    }

    @Override
    public void updateStats(ExtendedGame game, Position from, Position to) {
        int fromTerrainFactor = Utility2.getTerrainFactor(game, from);
        int fromFriendlySupport = Utility2.getFriendlySupport(game,from, game.getUnitAt(from).getOwner());

        int toTerrainFactor = Utility2.getTerrainFactor(game, to);
        int toFriendlySupport = Utility2.getFriendlySupport(game,from, game.getUnitAt(to).getOwner());

        fromAttack = game.getUnitAt(from).getAttackingStrength() + fromTerrainFactor + fromFriendlySupport;
        toDefense = game.getUnitAt(to).getDefensiveStrength() + toTerrainFactor + toFriendlySupport;
    }
}
