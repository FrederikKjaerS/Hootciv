package hotciv.variants.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.utility.Utility2;
import hotciv.variants.attackStrategy.dieDecisionStrategy.DieDecisionStrategy;

public class AlgoAttackStrategy implements AttackStrategy {
    private DieDecisionStrategy die;
    private int fromAttack;
    private int toDefense;

    public AlgoAttackStrategy(DieDecisionStrategy dieDecisionStrategy) {
        this.die = dieDecisionStrategy;
    }

    @Override
    public boolean unitWins(Game game, Position from, Position to) {
        fromAttack = getNewAttackStats(game,from);
        toDefense = getNewDefenseStats(game,to);
        return fromAttack > toDefense;
    }

    private int roll(){
        return die.getEyes();
    }

    public int getNewAttackStats(Game game, Position p) {
        int terrainFactor = Utility2.getTerrainFactor(game, p);
        int friendlySupport = Utility2.getFriendlySupport(game,p, game.getUnitAt(p).getOwner());

        return (game.getUnitAt(p).getAttackingStrength() + friendlySupport) * terrainFactor * roll();
    }
    public int getNewDefenseStats(Game game, Position p) {
        int terrainFactor = Utility2.getTerrainFactor(game, p);
        int friendlySupport = Utility2.getFriendlySupport(game,p, game.getUnitAt(p).getOwner());

        return (game.getUnitAt(p).getDefensiveStrength() + friendlySupport) * terrainFactor * roll();
    }
}
